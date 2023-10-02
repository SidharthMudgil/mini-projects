package com.bhaskarblur.webtalk.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import android.widget.Toast
import com.bhaskarblur.webtalk.R
import com.bhaskarblur.webtalk.databinding.ActivityReceiveCallScreenBinding
import com.bhaskarblur.webtalk.model.callModel
import com.bhaskarblur.webtalk.services.mainService
import com.bhaskarblur.webtalk.utils.callHandler
import com.bhaskarblur.webtalk.utils.callTypes
import com.bhaskarblur.webtalk.utils.firebaseHandler
import com.bhaskarblur.webtalk.utils.firebaseWebRTCHandler
import com.bhaskarblur.webtalk.utils.helper
import com.bhaskarblur.webtalk.utils.webRTCHandler
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.gson.Gson
import org.webrtc.IceCandidate

class callScreen : AppCompatActivity(), callHandler {

    private lateinit var binding: ActivityReceiveCallScreenBinding;
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReceiveCallScreenBinding.inflate(layoutInflater);
        setContentView(binding.root);
        val database = FirebaseDatabase.getInstance("YOUR_FIREABSE_DB_URL")
        userRef = database.getReference("Users");
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        loadData();
        manageLogic();
    }

    private fun manageLogic() {

        binding.rejectCall.setOnClickListener{
            firebaseHandler.answerUser(
            callModel(
                email, userName, receiverEmail, null, callTypes.Reject.name
            )
            )
            userRef.child(helper().cleanWord(email.toString())).child("latestEvents").setValue(null);
            userRef.child(helper().cleanWord(email.toString())).child("status").setValue("Online");
            finish()
            overridePendingTransition(R.anim.fade_2, R.anim.fade);
        }

        binding.acceptCall.setOnClickListener {
            firebaseHandler.answerUser(
                callModel(
                    email, userName, receiverEmail, null, callTypes.Answer.name
                )
            )
            firebaseHandler.answerUser(
                callModel(
                    email, userName, email, null, "Picked"
                )
            )
            var intent = Intent(this@callScreen, videoCallActivity::class.java)
            intent.putExtra("userName", receiverName);
            intent.putExtra("userEmail",  receiverEmail);
            intent.putExtra("callType",  callType);
            startActivity(intent);
            finish()

        }
    }

    private fun loadData() {
        email = prefs!!.getString("userEmail","")!!;

        receiverEmail = intent.getStringExtra("userEmail").toString();
        receiverName = intent.getStringExtra("userName").toString();
        var intent = intent;
        callType = intent.getStringExtra("callType").toString();

        if(callType.toString().lowercase().contains("video")) {
            binding.callType.setText("Video call");
        }
        else if(callType.toString().lowercase().contains("audio")) {
            binding.callType.setText("Audio call");
        }

        binding.userNameText.setText("From "+receiverName);


        email = prefs!!.getString("userEmail","")!!;
        userName = prefs!!.getString("userName","")!!;


        firebaseHandler = firebaseHandler(this, userRef, email, userName);
        firebaseWebRTCHandler = firebaseWebRTCHandler(this,userRef, email, userName
            , firebaseHandler);

        service = mainService(this, firebaseWebRTCHandler)
        service.setCallHandler(this, firebaseHandler);
        firebaseWebRTCHandler.initWebRTCClient(email);
        if(callType.toString().lowercase().contains("video")) {
            binding.callType.setText("Video");
            binding.userCamera.visibility = View.VISIBLE
            binding.userCameraOverlay.visibility = View.VISIBLE
            firebaseWebRTCHandler.initLocalSurfaceView(binding.userCamera, true);
        }
        else if(callType.toString().lowercase().contains("audio")) {
            binding.callType.setText("Audio");
            binding.userCamera.visibility = View.GONE
            binding.userCameraOverlay.visibility = View.GONE
        }
    }

    override fun onBackPressed() {
//        super.onBackPressed()
        // no action here
    }

    override fun onDestroy() {
        super.onDestroy()
        userRef.child(helper().cleanWord(email)).child("status").setValue("Online");
    }

    override fun onCallReceived(message: callModel) {

    }

    override fun onInitOffer(message: callModel) {


    }

    override fun onCallAccepted(message: callModel) {

    }

    override fun onCallRejected(message: callModel) {

    }

    override fun onCallCut(message: callModel) {
    }

    override fun onUserAdded(message: callModel) {

    }

    override fun finalCallAccepted(message: callModel) {
    }
}