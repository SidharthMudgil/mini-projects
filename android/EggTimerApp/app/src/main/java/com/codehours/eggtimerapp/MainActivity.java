package com.codehours.eggtimerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    SeekBar seekBar;
    TextView textView;
    boolean start = false;
    CountDownTimer countDownTimer;

    protected void setText(int ll) {
        int minutes = ll / 60;
        int seconds = ll - (minutes * 60);

        String time = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        textView.setText(time);
    }

    protected void timesUp() {
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.timesup);
        mediaPlayer.start();
    }

    public void startTimer(View view) {
        Button button = findViewById(R.id.button);

        if (start) {
            textView.setText("00:30");
            seekBar.setProgress(30);
            seekBar.setEnabled(true);
            countDownTimer.cancel();
            button.setText("Go!");
            start = false;
        } else {
            seekBar.setEnabled(false);
            button.setText("Stop!");
            start = true;

            countDownTimer = new CountDownTimer(seekBar.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick(long l) {
                    setText((int) l / 1000);
                }

                @Override
                public void onFinish() {
                    seekBar.setEnabled(true);
                    timesUp();
                    start = false;
                    button.setText("Go!");
                }
            }.start();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = findViewById(R.id.seekBar);
        textView = findViewById(R.id.textView);

        seekBar.setMin(1);
        seekBar.setMax(600);
        seekBar.setProgress(30);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                setText(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}