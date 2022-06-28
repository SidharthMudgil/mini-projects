package com.codehours.basicphrases;

import android.animation.Animator;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    androidx.gridlayout.widget.GridLayout gridLayout;

    protected void startAnimation() {
        //initially scaleX = 0, scaleY = 0, alpha = 1.0
        ImageView imageView = findViewById(R.id.indiaImageView);
        imageView.animate().scaleXBy(1).scaleYBy(1).setDuration(3000);

        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.indianflute);
        mediaPlayer.start();

        Animator.AnimatorListener animatorListener = new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                imageView.setVisibility(View.GONE);
                mediaPlayer.stop();
                gridLayout = findViewById(R.id.gridLayout);
                gridLayout.setVisibility(View.VISIBLE);
                animateImage();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        };

        //image fadeout effect
        new Handler().postDelayed(() -> imageView.animate().alpha(0.3f).setDuration(1000).setListener(animatorListener), 5000);
    }

    protected void animateImage() {
        ImageView img1 = findViewById(R.id.punjab);
        ImageView img2 = findViewById(R.id.haryana);
        ImageView img3 = findViewById(R.id.bihar);
        ImageView img4 = findViewById(R.id.westbengal);
        ImageView img5 = findViewById(R.id.gujrat);
        ImageView img6 = findViewById(R.id.mumbai);
        ImageView img7 = findViewById(R.id.kerala);
        ImageView img8 = findViewById(R.id.tamil);

        img1.animate().scaleXBy(1.5f).scaleYBy(1.5f).setDuration(500);
        img2.animate().scaleXBy(1.5f).scaleYBy(1.5f).setDuration(500);
        img3.animate().scaleXBy(1.5f).scaleYBy(1.5f).setDuration(500);
        img4.animate().scaleXBy(1.5f).scaleYBy(1.5f).setDuration(500);
        img5.animate().scaleXBy(1.5f).scaleYBy(1.5f).setDuration(500);
        img6.animate().scaleXBy(1.5f).scaleYBy(1.5f).setDuration(500);
        img7.animate().scaleXBy(1.5f).scaleYBy(1.5f).setDuration(500);
        img8.animate().scaleXBy(1.5f).scaleYBy(1.5f).setDuration(500);

        new Handler().postDelayed(() -> {
            img1.animate().scaleX(0.7f).scaleY(0.7f).setDuration(200);
            img2.animate().scaleX(0.7f).scaleY(0.7f).setDuration(200);
            img3.animate().scaleX(0.7f).scaleY(0.7f).setDuration(200);
            img4.animate().scaleX(0.7f).scaleY(0.7f).setDuration(200);
            img5.animate().scaleX(0.7f).scaleY(0.7f).setDuration(200);
            img6.animate().scaleX(0.7f).scaleY(0.7f).setDuration(200);
            img7.animate().scaleX(0.7f).scaleY(0.7f).setDuration(200);
            img8.animate().scaleX(0.7f).scaleY(0.7f).setDuration(200);
        }, 500);

        new Handler().postDelayed(() -> {
            img1.animate().scaleX(1).scaleY(1).setDuration(300);
            img2.animate().scaleX(1).scaleY(1).setDuration(300);
            img3.animate().scaleX(1).scaleY(1).setDuration(300);
            img4.animate().scaleX(1).scaleY(1).setDuration(300);
            img5.animate().scaleX(1).scaleY(1).setDuration(300);
            img6.animate().scaleX(1).scaleY(1).setDuration(300);
            img7.animate().scaleX(1).scaleY(1).setDuration(300);
            img8.animate().scaleX(1).scaleY(1).setDuration(300);
        }, 700);
    }

    public void sayPhrase(View view) {
        MediaPlayer mediaPlayer = null;
        String culture = "";
        ImageView imageView = (ImageView) view; //the image view
        int imageNo = Integer.parseInt(imageView.getTag().toString()); //the no. of image view

        androidx.gridlayout.widget.GridLayout gridLayout = findViewById(R.id.gridLayout);
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ImageView iv = (ImageView) gridLayout.getChildAt(i);
            iv.setClickable(false);
        }

        switch (imageNo) {
            case 0:
                mediaPlayer = MediaPlayer.create(this, R.raw.punjabi);
                culture = "punjab";
                break;
            case 1:
                mediaPlayer = MediaPlayer.create(this, R.raw.haryanvi);
                culture = "haryana";
                break;
            case 2:
                mediaPlayer = MediaPlayer.create(this, R.raw.bhojpuri);
                culture = "bihar";
                break;
            case 3:
                mediaPlayer = MediaPlayer.create(this, R.raw.bangla);
                culture = "west bengal";
                break;
            case 4:
                mediaPlayer = MediaPlayer.create(this, R.raw.gujrati);
                culture = "gujrat";
                break;
            case 5:
                mediaPlayer = MediaPlayer.create(this, R.raw.marathi);
                culture = "punjab";
                break;
            case 6:
                mediaPlayer = MediaPlayer.create(this, R.raw.keral);
                culture = "kerala";
                break;
            case 7:
                mediaPlayer = MediaPlayer.create(this, R.raw.tamil);
                culture = "tamilnadu";
                break;
            default:
                Log.i("Error", "tag not found");
        }
        //if media player is null then don't start the player
        assert mediaPlayer != null;
        mediaPlayer.start();

        Toast.makeText(this, culture, Toast.LENGTH_LONG).show();

        new Handler().postDelayed(() -> {
            for (int i = 0; i < gridLayout.getChildCount(); i++) {
                ImageView iv = (ImageView) gridLayout.getChildAt(i);
                iv.setClickable(true);
            }
        }, 1000);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startAnimation();
    }
}