package com.sidharth.sidharthlg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button aboutMeBtn;
    Button githubBtn;
    Button websiteBtn;
    Button discordBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_SidharthLG);
        setContentView(R.layout.activity_main);
        if (getSupportActionBar()!=null) {
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, R.color.lg_black)));
        }

        initializeButtons();
        handleClickEvents();
    }

//    method to initialize the buttons
    private void initializeButtons() {
        aboutMeBtn = findViewById(R.id.about_me);
        githubBtn = findViewById(R.id.github);
        websiteBtn = findViewById(R.id.website);
        discordBtn = findViewById(R.id.discord);
    }

//    method to setOnClickListeners to buttons
    private void handleClickEvents() {
        aboutMeBtn.setOnClickListener(v -> openURL("https://www.linkedin.com/in/sidharthmudgil"));

        githubBtn.setOnClickListener(v -> openURL("https://github.com/LiquidGalaxyLAB"));

        websiteBtn.setOnClickListener(v -> openURL("https://www.liquidgalaxy.eu/"));

        discordBtn.setOnClickListener(v -> openURL("https://discord.gg/peGA5K8tJU"));
    }

//    method to create an ACTION_VIEW intent to open the URLs
    private void openURL(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }
}