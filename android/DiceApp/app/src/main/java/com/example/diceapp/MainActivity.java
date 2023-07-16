package com.example.diceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.TypedArray;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button rollDiceButton;
    ImageView diceOne;
    ImageView diceTwo;

    MediaPlayer mediaPlayer;
    TypedArray faces;

    public void generateNumber(View view) {
        Random random = new Random();

        mediaPlayer.start();

        rollDiceButton.setClickable(false);
        YoYo.with(Techniques.Wobble).duration(400).repeat(5).playOn(diceOne);
        YoYo.with(Techniques.Wobble).duration(400).repeat(5).playOn(diceTwo);

        diceOne.setImageResource(faces.getResourceId(random.nextInt(faces.length()), 0));
        diceTwo.setImageResource(faces.getResourceId(random.nextInt(faces.length()), 0));
        rollDiceButton.setClickable(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rollDiceButton = findViewById(R.id.button);
        diceOne = findViewById(R.id.diceOneImage);
        diceTwo = findViewById(R.id.diceTwoImage);

        mediaPlayer = MediaPlayer.create(this, R.raw.sfx);
        faces = getResources().obtainTypedArray(R.array.diceFaces);
    }
}