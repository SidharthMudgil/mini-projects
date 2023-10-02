package com.bhaskarblur.webtalk.ui

import android.app.ProgressDialog
import android.content.ContentValues
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.widget.Toast
import com.bhaskarblur.webtalk.R
import com.bhaskarblur.webtalk.databinding.ActivityLoginScreenBinding
import com.bhaskarblur.webtalk.databinding.ActivitySignupScreenBinding
import com.bhaskarblur.webtalk.model.userModel
import com.bhaskarblur.webtalk.utils.firebaseHandler
import com.bhaskarblur.webtalk.utils.helper
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.messaging.FirebaseMessaging

class loginScreen : AppCompatActivity() {
    private lateinit var binding: ActivityLoginScreenBinding;
    private lateinit var userRef : DatabaseReference;
    private var prefs: SharedPreferences? = null;
    private var pushtToken: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginScreenBinding.inflate(layoutInflater);
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

        binding.signupTxt.setOnClickListener {
            startActivity(Intent(this@loginScreen, signupScreen::class.java))
            finish()
        }

        binding.loginBtn.setOnClickListener {

            if (binding.emailTxt.text.trim().toString().isEmpty()) {
                binding.emailTxt.setError("Enter email");
            } else if (binding.passTxt.toString().isEmpty()) {
                binding.passTxt.setError("Enter password");
            } else {

                // create a fireba  se user;

                val dialog = ProgressDialog.show(
                    this@loginScreen, "",
                    "Logging...", true
                )

               var login = userRef.child(helper().cleanWord(binding.emailTxt.text.toString())).get().addOnSuccessListener {
                    Log.i("firebase", "Got value ${it.value}")
                   dialog.cancel();
                    if(it.value!=null) {
                        var user : userModel? = it.getValue(userModel::class.java);

                        if(user!!.password.equals(binding.passTxt.text.toString())) {
                            Toast.makeText(this@loginScreen, "Login successful", Toast.LENGTH_SHORT).show();
                            val editor = prefs!!.edit()
                            firebaseHandler().updateNotification(pushtToken, this@loginScreen, userRef,
                                binding.emailTxt.text.toString());
                            editor.putBoolean("loggedStatus", true);
                            editor.putString("userEmail", binding.emailTxt.text.toString())
                            editor.putString("userName", user!!.username)
                            editor.apply()
                            editor.commit()
                            startActivity(Intent(this@loginScreen, mainActivity::class.java))
                            finish()
                        }
                        else {
                            Toast.makeText(this@loginScreen, "Incorrect password", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(this@loginScreen, "No such account exists", Toast.LENGTH_SHORT).show();
                    }

                }.addOnFailureListener{
                    dialog.cancel();
                   Toast.makeText(this@loginScreen, it.message.toString(), Toast.LENGTH_SHORT).show();
                    Log.e("firebase", "Error getting data", it)
                }

            }
        }
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left)
    }
}