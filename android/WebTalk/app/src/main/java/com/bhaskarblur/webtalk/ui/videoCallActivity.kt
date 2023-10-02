package com.bhaskarblur.webtalk.ui

import android.R.attr.width
import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.PorterDuff
import android.media.projection.MediaProjectionManager
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import com.bhaskarblur.webtalk.R
import com.bhaskarblur.webtalk.databinding.ActivityVideoCallBinding
import com.bhaskarblur.webtalk.di.hilRepo
import com.bhaskarblur.webtalk.model.callModel
import com.bhaskarblur.webtalk.model.isValid
import com.bhaskarblur.webtalk.services.mainService
import com.bhaskarblur.webtalk.services.mainServiceActions
import com.bhaskarblur.webtalk.utils.callHandler
import com.bhaskarblur.webtalk.utils.firebaseHandler
import com.bhaskarblur.webtalk.utils.firebaseWebRTCHandler
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import org.webrtc.IceCandidate
import org.webrtc.SessionDescription
import javax.inject.Inject


@AndroidEntryPoint
class videoCallActivity : AppCompatActivity(), callHandler {
    private lateinit var binding: ActivityVideoCallBinding;
    private lateinit var userRef : DatabaseReference;
    private lateinit var receiverEmail :String;
    private lateinit var receiverName :String;
    private lateinit var email :String;
    private var prefs: SharedPreferences? = null;
    lateinit var service: mainService;
    lateinit var userName : String;
    lateinit var firebaseWebRTCHandler: firebaseWebRTCHandler;
    lateinit var firebaseHandler: firebaseHandler;
    private var gson  = Gson();
    private var offerMade = false;
    private var accepted = false;
    private var videoHide = false;
    private var micMute = false;
    private var speaker = false;
    private var isSharing = false;
    private var callType = "";

    @Inject lateinit var hilRepo: hilRepo
    private lateinit var requestScreenCapture : ActivityResultLauncher<Intent>

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onStart() {
        super.onStart()
        requestScreenCapture = registerForActivityResult(ActivityResultContracts
            .StartActivityForResult()) {
            Log.d("Screen started","1");
                if(it.resultCode == Activity.RESULT_OK) {
                    val intent = it.data;
                    mainService.screenPermissionIntent = intent
                    service.startService(email, this, mainServiceActions.FOREGROUND_SERVICE_TYPE_MEDIA_PROJECTION  )
                    isSharing = true

                }
        }

    }
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoCallBinding.inflate(layoutInflater);
        setContentView(binding.root);
        val database = FirebaseDatabase.getInstance("YOUR_FIREABSE_DB_URL")
        userRef = database.getReference("Users");
        prefs = PreferenceManager.getDefaultSharedPreferences(this);

        loadData();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            manageLogic()
        };
    }

    private fun manageLogic() {


        binding.cutCall.setOnClickListener {
            firebaseWebRTCHandler.endCall();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                service.stopAudio()
            };
            service.stopSelf();
            finish();
        }

    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun loadData() {
        email = prefs!!.getString("userEmail","")!!;
        userName = prefs!!.getString("userName","")!!;

        var intent = intent;
        receiverEmail = intent.getStringExtra("userEmail").toString();
        receiverName = intent.getStringExtra("userName").toString();
        callType = intent.getStringExtra("callType").toString();

        if(callType.toString().lowercase().contains("video")) {
            binding.callType.setText("Video call");
        }
        else if(callType.toString().lowercase().contains("audio")) {
            binding.callType.setText("Audio call");
        }

        binding.userNameText.setText("On call with "+receiverName);
        firebaseHandler = firebaseHandler(this, userRef, email, userName);
        firebaseWebRTCHandler = firebaseWebRTCHandler(this,userRef, email, userName
            , firebaseHandler);

        firebaseWebRTCHandler.setTarget(receiverEmail);

        firebaseWebRTCHandler.initWebRTCClient(email);
        hilRepo.initWebRTCHandler(email);
        if(callType.lowercase().contains("video")) {
            firebaseWebRTCHandler.initLocalSurfaceView(binding.userCamera, true);
            firebaseWebRTCHandler.initRemoteSurfaceView(binding.otherUserCamera);
        }
        else {
            binding.userCamera.visibility = View.GONE
            binding.otherUserCamera.visibility = View.GONE
            binding.swapbtn.visibility = View.GONE
            binding.videobtn.visibility = View.GONE
            binding.broadcastbtn.visibility = View.GONE
            firebaseWebRTCHandler.initLocalSurfaceView(binding.userCamera, false);
            firebaseWebRTCHandler.initRemoteSurfaceView(binding.otherUserCamera);
        }
        service = mainService(this, firebaseWebRTCHandler)
        service.setCallHandler(this, firebaseHandler);
        service.setWebRtchandler(firebaseWebRTCHandler.webRTCHandler)
        service.startService(email, this, mainServiceActions.START_SERVICE);
            service.localSurfaceView = binding.userCamera;
        service.remoteSurfaceView = binding.otherUserCamera;

        binding.swapbtn.setOnClickListener{
            firebaseWebRTCHandler.switchCamera();
        }

        binding.videobtn.setOnClickListener {
            if(!videoHide) {
                binding.videobtn.setImageResource(R.drawable.videooff);
                firebaseWebRTCHandler.toggleVideo(true);
                videoHide = true;
                binding.paustext.visibility = View.VISIBLE
                binding.videobtn.setBackgroundTintList(getResources().getColorStateList(R.color.white));
                binding.videobtn.setColorFilter(ContextCompat.getColor(this, R.color.blackLight), android.graphics.PorterDuff.Mode.SRC_IN);
            }
            else {
                binding.videobtn.setImageResource(R.drawable.videoon);
                firebaseWebRTCHandler.toggleVideo(false);
                binding.paustext.visibility = View.GONE
                videoHide=false;
                binding.videobtn.setBackgroundTintList(getResources().getColorStateList(R.color.blackLight));
                binding.videobtn.setColorFilter(ContextCompat.getColor(this, R.color.white), android.graphics.PorterDuff.Mode.SRC_IN);
            }
        }

        binding.micbtn.setOnClickListener {
            if(!micMute) {
                binding.micbtn.setImageResource(R.drawable.microphoneoff);
                firebaseWebRTCHandler.toggleAudio(true);
                micMute = true;
                binding.micbtn.setBackgroundTintList(getResources().getColorStateList(R.color.white));
                binding.micbtn.setColorFilter(ContextCompat.getColor(this, R.color.blackLight), android.graphics.PorterDuff.Mode.SRC_IN);
            }
            else {
                binding.micbtn.setImageResource(R.drawable.microphone);
                firebaseWebRTCHandler.toggleAudio(false);
                micMute=false;
                binding.micbtn.setBackgroundTintList(getResources().getColorStateList(R.color.blackLight));
                binding.micbtn.setColorFilter(ContextCompat.getColor(this, R.color.white), android.graphics.PorterDuff.Mode.SRC_IN);
            }
        }

        binding.speakericon.setOnClickListener {
            if(!speaker) {
                binding.speakericon.setImageResource(R.drawable.speakericon);
                service.toggleAudioSpeakerMode(false);
                speaker = true;
                binding.speakericon.setBackgroundTintList(getResources().getColorStateList(R.color.white));
                binding.speakericon.setColorFilter(ContextCompat.getColor(this, R.color.blackLight), android.graphics.PorterDuff.Mode.SRC_IN);

            }
            else {
                speaker = false;
                service.toggleAudioSpeakerMode(true);
                binding.speakericon.setImageResource(R.drawable.mobileicon);
                binding.speakericon.setBackgroundTintList(getResources().getColorStateList(R.color.blackLight));
                binding.speakericon.setColorFilter(ContextCompat.getColor(this, R.color.white), android.graphics.PorterDuff.Mode.SRC_IN);
            }
        }

        binding.broadcastbtn.setOnClickListener {
            if(!isSharing) {
                isSharing = true

                AlertDialog.Builder(this@videoCallActivity)
                    .setTitle("Screen sharing?")
                    .setMessage("Do you want to present your screen to the other user?")
                    .setPositiveButton("Yes", object : DialogInterface.OnClickListener{
                        override fun onClick(p0: DialogInterface?, p1: Int) {
                            isSharing = false
                            binding.broadcastbtn.setImageResource(R.drawable.screenoff)
                            binding.broadcastbtn.setBackgroundTintList(getResources().getColorStateList(R.color.white));
                            binding.broadcastbtn.setColorFilter(ContextCompat.getColor(this@videoCallActivity, R.color.blackLight), PorterDuff.Mode.SRC_IN);
                            binding.videobtn.visibility = View.GONE
                            binding.swapbtn.visibility = View.GONE
                            startScreenShare();
                        }

                    })
                    .setNegativeButton("No", object : DialogInterface.OnClickListener{
                        override fun onClick(p0: DialogInterface?, p1: Int) {

                        }
                        }).show();

            }
            else {
                isSharing = false
                binding.videobtn.visibility = View.VISIBLE
                binding.swapbtn.visibility = View.VISIBLE
                service.startService(email, this, mainServiceActions.STOP_PROJECTION  )
                binding.broadcastbtn.setImageResource(R.drawable.screenon)
                binding.broadcastbtn.setBackgroundTintList(getResources().getColorStateList(R.color.blackLight));
                binding.broadcastbtn.setColorFilter(ContextCompat.getColor(this, R.color.white), android.graphics.PorterDuff.Mode.SRC_IN);
            }
        }
    }

    private fun startScreenShare() {
        val screenManager = application.getSystemService(
            Context.MEDIA_PROJECTION_SERVICE) as MediaProjectionManager

        val captureIntent = screenManager.createScreenCaptureIntent();
        requestScreenCapture.launch(captureIntent);


    }

    override fun onBackPressed() {
    }

    override fun onCallReceived(message: callModel) {

    }

    override fun onInitOffer(message: callModel) {
        if(message.isValid() && !offerMade) {
            offerMade = true
//            Toast.makeText(this, "Offered " + message.senderEmail, Toast.LENGTH_SHORT).show()
//            Log.d("offered", message.senderEmail.toString());
            firebaseWebRTCHandler.webRTCHandler.onRemoteSessionReceived(
                SessionDescription(
                    SessionDescription.Type.OFFER,
                    message.callData.toString()
                ),
                message.senderEmail!!
            )

            firebaseWebRTCHandler.acceptCall(message.senderEmail!!);
        }
    }

    override fun onCallAccepted(message: callModel) {

        firebaseWebRTCHandler.startCall(receiverEmail, "Offer")
    }

    override fun onCallRejected(message: callModel) {
    }

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCallCut(message: callModel) {
//        Toast.makeText(this, "Call Ended", Toast.LENGTH_SHORT).show()
        firebaseWebRTCHandler.endCall();
        firebaseHandler.changeMyStatus("Online");
        finish();

    }

    override fun onUserAdded(message: callModel) {
        Log.d("usedAdded", message.senderEmail.toString());
        val candidate : IceCandidate? = try {
            gson.fromJson(message.callData.toString(),IceCandidate::class.java);

        } catch (e:Exception) {
          null;
        }

        candidate?.let {
            firebaseWebRTCHandler.webRTCHandler.addIceCandidateToPeer(candidate);
//            firebaseWebRTCHandler.webRTCHandler.sendIceCandidate(receiverEmail, it);
        }
    }

    override fun finalCallAccepted(message: callModel) {
        if(message.isValid() && !accepted) {
            accepted = true;
            Log.d("acceptedFinal", message.senderEmail.toString());
            firebaseWebRTCHandler.webRTCHandler.onRemoteSessionReceived(
                SessionDescription(
                    SessionDescription.Type.ANSWER,
                    message.callData.toString()
                ),
                message.senderEmail!!
            )

//            Toast.makeText(this, "Starting Call", Toast.LENGTH_SHORT).show()
        }
    }

    override fun finish() {
        super.finish()
        firebaseWebRTCHandler.endCall();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            service.stopAudio()
        };
        service.stopSelf();

    }
    override fun onDestroy() {
        super.onDestroy()
        firebaseWebRTCHandler.endCall();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            service.stopAudio()
        };
        service.stopSelf();


    }
}