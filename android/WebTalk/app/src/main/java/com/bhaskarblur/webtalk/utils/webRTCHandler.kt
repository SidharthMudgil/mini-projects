package com.bhaskarblur.webtalk.utils

import android.content.Context
import android.content.Intent
import android.media.projection.MediaProjection
import android.os.Handler
import android.provider.MediaStore.Video
import android.util.DisplayMetrics
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import com.bhaskarblur.webtalk.model.callModel
import com.google.gson.Gson
import org.webrtc.AudioTrack
import org.webrtc.Camera2Enumerator
import org.webrtc.CameraVideoCapturer
import org.webrtc.DefaultVideoDecoderFactory
import org.webrtc.DefaultVideoEncoderFactory
import org.webrtc.EglBase
import org.webrtc.IceCandidate
import org.webrtc.MediaConstraints
import org.webrtc.MediaStream
import org.webrtc.PeerConnection
import org.webrtc.PeerConnectionFactory
import org.webrtc.RendererCommon.RendererEvents
import org.webrtc.ScreenCapturerAndroid
import org.webrtc.SdpObserver
import org.webrtc.SessionDescription
import org.webrtc.SurfaceTextureHelper
import org.webrtc.SurfaceViewRenderer
import org.webrtc.VideoCapturer
import org.webrtc.VideoTrack

class webRTCHandler  {

    private var localScreenShareVideoTrack: VideoTrack? = null
    private lateinit var context : Context;
    private val gsonObject : Gson;
    private val eglBaseContext = EglBase.create().eglBaseContext;
    private val peerConnectionFactory by lazy { createPeerConnectionFactory() }
    private var peerConnectionInstance : PeerConnection? = null;
    private val iceServer = listOf(
//        PeerConnection.IceServer.builder("turn:a.relay.metered.ca:443?transport=tcp")
//            .setUsername("83eebabf8b4cce9d5dbcb649")
//            .setPassword("2D7JvfkOQtBdYW3R").createIceServer(),
                PeerConnection.IceServer.builder("stun:stun.l.google.com:19302").createIceServer()
    )

    val rtcConfig = PeerConnection.RTCConfiguration(
        arrayListOf(
            PeerConnection.IceServer.builder("turn:a.relay.metered.ca:443?transport=tcp")
            .setUsername("83eebabf8b4cce9d5dbcb649")
            .setPassword("2D7JvfkOQtBdYW3R").createIceServer(),
//                    PeerConnection.IceServer.builder("stun:stun.l.google.com:19302").createIceServer()
        )
    ).apply {
        enableRtpDataChannel = true
    }
    private lateinit var userEmail : String;
    private lateinit var userName : String;
    private val localVideoSource by lazy { peerConnectionFactory.createVideoSource(false) }
    private val localAudioSource by lazy { peerConnectionFactory.createAudioSource(MediaConstraints())}
    private lateinit var videoCapturer : CameraVideoCapturer;
    private lateinit var screenCapturer : VideoCapturer;
    private var surfaceTextureHelper: SurfaceTextureHelper?=null
    private lateinit var localSurfaceView : SurfaceViewRenderer;
    private lateinit var remoteSurfaceView: SurfaceViewRenderer;
    private var localStream : MediaStream? = null
    private var localTrackId = "_";
    private var localStreamId = "";
    private var localAudioTrack: AudioTrack? = null
    private var localVideoTrack: VideoTrack? = null
    private var firebaseHandler : firebaseHandler ;
    private var permissionIntent : Intent? = null
    private val localScreenVideoSource by lazy { peerConnectionFactory.createVideoSource(false) }
    private val mediaConstraint = MediaConstraints().apply {
        mandatory.add(MediaConstraints.KeyValuePair("OfferToReceiveVideo","true"))
        mandatory.add(MediaConstraints.KeyValuePair("OfferToReceiveAudio","true"))
    }
    constructor(context: Context, gsonObject: Gson, firebaseHandler: firebaseHandler) {
        this.context = context
        this.gsonObject = gsonObject
        this.firebaseHandler = firebaseHandler
        videoCapturer = getVideoCapturer(context)
        initPeerConnectionFactory();
    }


    fun initializeWebRTCClient(
        userEmail : String, observer: PeerConnection.Observer, userName: String
    ) {
        this.userName = userName;
        this.userEmail = userEmail;
        localTrackId = "${userEmail}_track"
        localStreamId = "${userEmail}_stream"
        peerConnectionInstance = createPeerConnection(observer)
    }

    private fun createPeerConnection(observer: PeerConnection.Observer): PeerConnection? {
        return peerConnectionFactory.createPeerConnection(rtcConfig, observer)

    }

    private fun initPeerConnectionFactory() {
        val options = PeerConnectionFactory.InitializationOptions.builder(context)
            .setEnableInternalTracer(true).setFieldTrials("WebRTC-H264HighProfile/Enabled/")
            .createInitializationOptions()

        PeerConnectionFactory.initialize(options);

    }

    private fun initSurfaceView(view : SurfaceViewRenderer) {
        view.run {
            setMirror(false);
            setEnableHardwareScaler(true)
            init(eglBaseContext, object : RendererEvents{
                override fun onFirstFrameRendered() {
                    Log.d("firstframe","y");
                }

                override fun onFrameResolutionChanged(p0: Int, p1: Int, p2: Int) {
                }

            })

        }
    }

    fun initRemoteSurfaceView(localview : SurfaceViewRenderer ) {
        this.remoteSurfaceView = localview;
        initSurfaceView(remoteSurfaceView)
    }
    fun initLocalSurfaceView(localview : SurfaceViewRenderer, isVideoCall : Boolean) {
        this.localSurfaceView = localview;
        initSurfaceView(localview)
        startLocalStraming(localview, isVideoCall)
    }

    private fun startLocalStraming(localview: SurfaceViewRenderer, videoCall: Boolean) {
        localStream = peerConnectionFactory.createLocalMediaStream(localStreamId);
        if(videoCall) {
            startCapturingCamera(localview)
        }

        localAudioTrack = peerConnectionFactory.createAudioTrack(localTrackId+"_audio", localAudioSource)
        localStream?.addTrack(localAudioTrack);
        localStream?.videoTracks?.forEach({
//            peerConnectionInstance?.addTrack(it);
        })
        peerConnectionInstance?.addStream(localStream);
    }

    private fun startCapturingCamera(localview: SurfaceViewRenderer) {
        surfaceTextureHelper = SurfaceTextureHelper.create(
            Thread.currentThread().name, eglBaseContext
        )

        videoCapturer.initialize(
            surfaceTextureHelper, context, localVideoSource.capturerObserver

        )
        videoCapturer.startCapture(1080, 720, 60);

        localVideoTrack = peerConnectionFactory.createVideoTrack(localTrackId+"_video"
        , localVideoSource);

        localVideoTrack?.addSink(localview)
        localStream?.addTrack(localVideoTrack);
    }

    private fun getVideoCapturer(context : Context) : CameraVideoCapturer =
        Camera2Enumerator(context).run {
            deviceNames.find {
                isFrontFacing(it)

            }?.let {
                createCapturer(it, null)
            }?: throw IllegalStateException()
        }

    private fun stopCapturingCamer(localview: SurfaceViewRenderer) {
        videoCapturer.dispose()
        localVideoTrack?.removeSink(localSurfaceView)
        localSurfaceView.clearImage();
        localStream?.removeTrack(localVideoTrack);
        localVideoTrack?.dispose();
    }

    private fun createPeerConnectionFactory() : PeerConnectionFactory {
        return PeerConnectionFactory.builder()
            .setVideoDecoderFactory(   DefaultVideoDecoderFactory(eglBaseContext))
            .setVideoEncoderFactory(DefaultVideoEncoderFactory(eglBaseContext,
                true, true)).setOptions(
                    PeerConnectionFactory.Options().apply {
                        disableNetworkMonitor = false
                        disableEncryption = false;
                    }
                ).createPeerConnectionFactory();
    }

    fun call(target: String, callType: String): Boolean {
        var success = false;
        Log.d("isCalling", "yes");
        peerConnectionInstance!!.createOffer(object: SdpObserver {
            override fun onCreateSuccess(desc: SessionDescription?) {
                Log.d("statusCall_2", "createdCallSuccess")
                peerConnectionInstance?.setLocalDescription(
                    object : SdpObserver {
                        override fun onCreateSuccess(p0: SessionDescription?) {
                            Log.d("statusCall", "createdCallSuccess")
                        }

                        override fun onSetSuccess() {
                            Log.d("statusCall", "setCallSuccessthis")
                            success =
                                true;
//                            Log.d("sdpnew", desc?.description.toString())

                            firebaseHandler.callUser(
                                callModel(userEmail, userName, target, desc?.description
                                    , callType)
                            )
                        }

                        override fun onCreateFailure(p0: String?) {
                            Log.d("statusCall", "createdCallFailed")
                        }

                        override fun onSetFailure(p0: String?) {
                            Log.d("statusCall", "createdSetFailure")
                        }
                    }, desc)
            }

            override fun onSetSuccess() {
                Log.d("statusCall_2", "createdSetSuccess")
            }

            override fun onCreateFailure(p0: String?) {
                Log.d("statusCall_2", "createdCallFailure")
            }

            override fun onSetFailure(p0: String?) {
                Log.d("statusCall_2", "createdSetFailure")
            }
        }, mediaConstraint)
        Log.d("callResult",success.toString());
        return success;
    }

    fun answer(target:String): Boolean {
        var success = false;
        peerConnectionInstance?.createAnswer(object  : SdpObserver {
            override fun onCreateSuccess(desc: SessionDescription?) {

                peerConnectionInstance?.setLocalDescription(object : SdpObserver {
                    override fun onCreateSuccess(p0: SessionDescription?) {
                        TODO("Not yet implemented")
                    }

                    override fun onSetSuccess() {
                        Log.d("success", "success!!");
                        firebaseHandler.answerUser(  callModel(
                            userEmail, userName, target, desc?. description
                            , callTypes.FinalAnswer.name));
                        success=true;
                    }

                    override fun onCreateFailure(p0: String?) {
                        Log.d("statusFailed_", p0.toString());
                    }

                    override fun onSetFailure(p0: String?) {
                        Log.d("statusFailed_2", p0.toString());
                    }

                }, desc)
            }

            override fun onSetSuccess() {
                Log.d("successet", "SetSuccess")
            }

            override fun onCreateFailure(p0: String?) {
                Log.d("statusFailed2", p0.toString());
            }

            override fun onSetFailure(p0: String?) {
                Log.d("statusFailed3", p0.toString());
            }
        }, mediaConstraint)
        Log.d("acceptresult",success.toString());
        return success;
    }

    fun onRemoteSessionReceived(sdp : SessionDescription, target: String)
    {
        Log.d("remote received", sdp.type.toString());
        peerConnectionInstance?.setRemoteDescription(object : SdpObserver {
            override fun onCreateSuccess(p0: SessionDescription?) {
                Log.d("remotedesc1", p0?.type.toString());

            }

            override fun onSetSuccess() {
                Log.d("remotedesc0","");
            }

            override fun onCreateFailure(p0: String?) {
                Log.d("remotedesc2", p0!!);
            }

            override fun onSetFailure(p0: String?) {
                Log.d("remotedesc3", p0!!);

            }

        },sdp)


    }

    fun addIceCandidateToPeer(iceCandidate: IceCandidate) {
        peerConnectionInstance?.addIceCandidate(iceCandidate);
        Log.d("icePeerServer",iceCandidate.serverUrl)
    }

    fun sendIceCandidate(target: String, iceCandidate: IceCandidate) {

        addIceCandidateToPeer(iceCandidate);
        firebaseHandler.answerUser(  callModel(
            userEmail, userName, target, gsonObject.toJson(iceCandidate)
            , callTypes.ICECandidate.name));
        Log.d("iceCallData", gsonObject.toJson(iceCandidate).toString())
        Log.d("iceCallDataTarget", target)
    }

    fun closeConnection() {
        try {
            videoCapturer?.dispose();
            screenCapturer?.dispose()
            localStream?.dispose();
            peerConnectionInstance?.close();
        } catch (e: Exception){
            Log.d("error closing", e.message.toString());
        }
    }

    fun switchCamera() {
        videoCapturer.switchCamera(null);
    }
    fun toggleAudio(shouldBeMuted : Boolean) {
        if(shouldBeMuted) {
            localStream?.removeTrack(localAudioTrack);
        }
        else {
            localStream?.addTrack(localAudioTrack);
        }

    }

    fun toggleVideo(shouldHide : Boolean) {
        try {
            if(shouldHide) {
                stopCapturingCamer(localSurfaceView)
            }
            else {
                startCapturingCamera(localSurfaceView)
            }
        } catch (e: Exception){

        }
    }

    private fun createScreenCapturer() : VideoCapturer {
        return ScreenCapturerAndroid(permissionIntent, object : MediaProjection.Callback() {
            override fun onStop() {
                super.onStop()

            }
        })
    }
    private fun setPermissionIntent(screenPermissionIntent: Intent) {
        this.permissionIntent = screenPermissionIntent
    }
    fun setScreenCapturer(screenPermissionIntent: Intent) {
        setPermissionIntent(screenPermissionIntent);

    }

    fun toggleScreenShare(start: Boolean) {
        if(start) {
            Log.d("Startedscreen_2", "yes")
            startScreenCapturing();
        }
        else {
            stopScreenCapturing();
        }
    }

    fun startScreenCapturing() {
        val displayMetrics = DisplayMetrics()
        val windowsManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        windowsManager.defaultDisplay.getMetrics(displayMetrics)

        val screenWidthPixels = displayMetrics.widthPixels
        val screenHeightPixels = displayMetrics.heightPixels

        val surfaceTextureHelper = SurfaceTextureHelper.create(
            Thread.currentThread().name,eglBaseContext
        )

        Log.d("Startedscreen", "yes")
        screenCapturer = createScreenCapturer()

        screenCapturer.initialize(
            surfaceTextureHelper,context,localScreenVideoSource.capturerObserver
        )
        screenCapturer.startCapture(screenWidthPixels,screenHeightPixels,15)

        localScreenShareVideoTrack =
            peerConnectionFactory.createVideoTrack(localTrackId+"_video",localScreenVideoSource)
        localScreenShareVideoTrack?.addSink(localSurfaceView)
        localStream?.addTrack(localScreenShareVideoTrack)
        peerConnectionInstance?.addStream(localStream)
    }

     fun stopScreenCapturing() {

        screenCapturer?.stopCapture()
        screenCapturer?.dispose()
        localScreenShareVideoTrack?.removeSink(localSurfaceView)
        localSurfaceView.clearImage()
        localStream!!.removeTrack(localScreenShareVideoTrack)
        startCapturingCamera(localSurfaceView)
        localAudioTrack = peerConnectionFactory.createAudioTrack(localTrackId+"_audio", localAudioSource)
        localStream?.addTrack(localAudioTrack);
        localStream?.videoTracks?.forEach({
//            peerConnectionInstance?.addTrack(it);
        })
        peerConnectionInstance?.addStream(localStream);
    }


}