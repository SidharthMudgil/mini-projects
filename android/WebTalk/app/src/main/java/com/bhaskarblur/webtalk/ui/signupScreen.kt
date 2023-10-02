package com.bhaskarblur.webtalk.ui

import android.app.ProgressDialog
import android.content.ContentValues
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import com.bhaskarblur.webtalk.utils.helper
import android.widget.Toast
import com.bhaskarblur.webtalk.R
import com.bhaskarblur.webtalk.databinding.ActivitySignupScreenBinding
import com.bhaskarblur.webtalk.model.userModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging

class signupScreen : AppCompatActivity() {
    private var pushtToken: String = ""
    private lateinit var binding: ActivitySignupScreenBinding;
    private lateinit var userRef : DatabaseReference;
    private var prefs: SharedPreferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupScreenBinding.inflate(layoutInflater);
        setContentView(binding.root);
        val database = FirebaseDatabase.getInstance("YOUR_FIREABSE_DB_URL")
        userRef = database.getReference("Users");
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        manageLogic();
        getPushToken()
    }

    private fun getPushToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(ContentValues.TAG, "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result

            pushtToken = token;
            Log.d("push noti token", pushtToken);
        })
    }

    private fun manageLogic() {

        binding.loginTxt.setOnClickListener({
            finish();
        })

        binding.signupBtn.setOnClickListener {

            if (binding.userNameText.text.trim().toString().isEmpty()) {
                binding.userNameText.setError("Enter username");
            }
            else if (binding.emailTxt.text.trim().toString().isEmpty()) {
                binding.emailTxt.setError("Enter email");
            } else if (binding.passTxt.toString().isEmpty()) {
                binding.passTxt.setError("Enter password");
            } else {

                // create a fireba  se user;

                val dialog = ProgressDialog.show(
                    this@signupScreen, "",
                    "Creating Account...", true
                )
                var data : userModel = userModel(binding.userNameText.text.toString(),
                    binding.emailTxt.text.toString(), binding.passTxt.text.toString());

                userRef.child(helper().cleanWord(binding.emailTxt.text.toString())).setValue(data).addOnCompleteListener {

                    if(it.isSuccessful) {
                        Toast.makeText(this@signupScreen, "Sign up successful", Toast.LENGTH_SHORT).show();
                        val editor = prefs!!.edit()
                        editor.putBoolean("loggedStatus", true);
                        editor.putString("userEmail", binding.emailTxt.text.toString())
                        editor.putString("userName", binding.userNameText.text.toString())
                        editor.apply()
                        editor.commit()
                        dialog.cancel();
                        startActivity(Intent(this@signupScreen, mainActivity::class.java))
                        finish()
                    }


                }.addOnFailureListener {
                    dialog.cancel();
                    Toast.makeText(this@signupScreen, it.message, Toast.LENGTH_SHORT).show();
                }.addOnCanceledListener {
                    dialog.cancel();
                    Toast.makeText(this@signupScreen, "Cancelled", Toast.LENGTH_SHORT).show();
                }


            }
        }
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left)
    }
}