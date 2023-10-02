package com.bhaskarblur.webtalk.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.View
import android.widget.Toast
import com.bhaskarblur.webtalk.R
import com.bhaskarblur.webtalk.databinding.ActivityMakeCallBinding
import com.bhaskarblur.webtalk.model.callModel
import com.bhaskarblur.webtalk.services.mainService
import com.bhaskarblur.webtalk.utils.callHandler
import com.bhaskarblur.webtalk.utils.firebaseHandler
import com.bhaskarblur.webtalk.utils.firebaseWebRTCHandler
import com.bhaskarblur.webtalk.utils.helper
import com.bhaskarblur.webtalk.utils.webRTCHandler
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.gson.Gson
import org.webrtc.SessionDescription

class makeCall : AppCompatActivity(), callHandler {

    private lateinit var binding: ActivityMakeCallBinding;
    private lateinit var userRef : DatabaseReference;
    private lateinit var receiverEmail :String;
    private lateinit var receiverName :String;
    private lateinit var callType :String;
    private lateinit var email :String;
    private lateinit var userName :String;
    private var prefs: SharedPreferences? = null;
    lateinit var service: mainService;
    lateinit var firebaseWebRTCHandler: firebaseWebRTCHandler;
    lateinit var firebaseHandler: firebaseHandler;
    lateinit var rtcHandler: webRTCHandler;
    private var videoOpened = false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMakeCallBinding.inflate(layoutInflater);
        setContentView(binding.root);
        val database = FirebaseDatabase.getInstance("YOUR_FIREABSE_DB_URL")
        userRef = database.getReference("Users");
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        loadData();
        manageLogic();
    }

    private fun manageLogic() {

        binding.rejectCall2.setOnClickListener {
            userRef.child(helper().cleanWord(receiverEmail)).child("latestEvents").setValue(null)
            finish()
        }
    }

    private fun loadData() {
        email = prefs!!.getString("userEmail","")!!;
        userName = prefs!!.getString("userName","")!!;
        var intent = intent;
        receiverEmail = intent.getStringExtra("userEmail").toString();
        receiverName = intent.getStringExtra("userName").toString();
        callType = intent.getStringExtra("callType").toString();



        binding.userNameText.setText("Calling "+receiverName);


        firebaseHandler = firebaseHandler(this, userRef, email, userName);
        firebaseWebRTCHandler = firebaseWebRTCHandler(this,userRef, email, userName
            , firebaseHandler);

        service = mainService(this, firebaseWebRTCHandler)
        service.setCallHandler(this, firebaseHandler);
        rtcHandler = webRTCHandler(this, Gson(), firebaseHandler);
        firebaseWebRTCHandler.setTarget(receiverEmail);
        firebaseWebRTCHandler.initWebRTCClient(email);
        firebaseWebRTCHandler.setTarget(receiverEmail);

        ;

        if(callType.toString().lowercase().contains("video")) {
            binding.callType.setText("Video");
            binding.userCamera.visibility = View.VISIBLE
            binding.userCameraOverlay.visibility = View.VISIBLE
            firebaseWebRTCHandler.initLocalSurfaceView(binding.userCamera, true);
        }
        else if(callType.toString().lowercase().contains("audio")) {
            binding.callType.setText("Audio");
            binding.userCameraOverlay.visibility = View.GONE
            binding.userCamera.visibility = View.GONE
        }

    }

    override fun onCallReceived(message: callModel) {
    }

    override fun onInitOffer(message: callModel) {
        firebaseWebRTCHandler.acceptCall(receiverEmail)

    }

    override fun onCallAccepted(message: callModel) {
        var intent = Intent(this@makeCall, videoCallActivity::class.java)
        intent.putExtra("userName", message.senderName);
        intent.putExtra("userEmail",message.senderEmail);
        intent.putExtra("callType",  callType);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_2, R.anim.fade);
        finish()
    }

    override fun onCallRejected(message: callModel) {
//        Toast.makeText(this, "Call Rejected", Toast.LENGTH_SHORT).show()
        firebaseHandler.changeMyStatus("Online");
        finish();
    }

    override fun onCallCut(message: callModel) {
    }

    override fun onUserAdded(message: callModel) {


    }

    override fun finalCallAccepted(message: callModel) {
    }
}