package com.bhaskarblur.webtalk.ui

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import com.bhaskarblur.webtalk.R
import com.bhaskarblur.webtalk.databinding.ActivityAudioCallBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class audioCall : AppCompatActivity() {

    private lateinit var binding: ActivityAudioCallBinding;
    private lateinit var userRef : DatabaseReference;
    private lateinit var receiverEmail :String;
    private lateinit var receiverName :String;
    private lateinit var email :String;
    private var prefs: SharedPreferences? = null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_audio_call)
        binding = ActivityAudioCallBinding.inflate(layoutInflater);
        setContentView(binding.root);
        val database = FirebaseDatabase.getInstance("YOUR_FIREABSE_DB_URL")
        userRef = database.getReference("Users");
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        loadData();
    }

    private fun loadData() {

    }
}