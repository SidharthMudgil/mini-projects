package com.codehours.braintrainerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button startButton;
    TextView titleText;
    ImageView titleLogo;
    ImageView gameLogo;
    TextView timeLeft;
    TextView questionIndex;
    TextView questionText;
    Button playAgainButton;
    TextView scoreBoard;
    ConstraintLayout constraintLayout;
    androidx.gridlayout.widget.GridLayout gridLayout;
    CountDownTimer countdown;

    MediaPlayer tickSFX;
    MediaPlayer timesUp;
    MediaPlayer rightAnswer;
    MediaPlayer wrongAnswer;

    int score = 0;
    int questionCount = 0;
    int answer = 0;

    //function to generate random arithmetic operation questions <+ - % *>
    // + -> addition
    // - -> subtraction
    // % -> modulus (remainder)
    // * -> multiplication
    private void generateQuestion() {
        questionCount++;

        Random random = new Random();
        int a = random.nextInt(100) + 1;
        int b = random.nextInt(100) + 1;
        char operator = '?';

        //randomly generate operators
        switch (random.nextInt(4)) {
            case 0:
                operator = '+';
                answer = a + b;
                break;
            case 1:
                operator = '-';
                answer = a - b;
                break;
            case 2:
                operator = '*';
                answer = a * b;
                break;
            case 3:
                operator = '%';
                answer = a % b;
                break;
        }

        questionIndex.setText(String.format(Locale.getDefault(), "%d/10", questionCount));
        questionText.setText(String.format(Locale.getDefault(), "%02d %c %02d", a, operator, b));

        int correctIndex = random.nextInt(3);

        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            Button btn = (Button) gridLayout.getChildAt(i);

            if (i == correctIndex) {
                btn.setText(String.valueOf(answer));
            } else {
                String option;
                try {
                    option = String.valueOf((random.nextInt(answer + 5) - 5) * (random.nextBoolean() ? 1 : -1));
                } catch (IllegalArgumentException e) {
                    option = String.valueOf((random.nextInt(-1 * answer + 5) - 5) * (random.nextBoolean() ? 1 : -1));
                }
                btn.setText(option);
            }
        }
    }

    //function will call either on timesUp or questionsComplete conditions
    private void gameOver() {
        countdown.cancel();

        questionText.setVisibility(View.GONE);
        gridLayout.setVisibility(View.GONE);

        scoreBoard.setText(String.format(Locale.getDefault(), "score:%02d", score));

        tickSFX.release();

        scoreBoard.setVisibility(View.VISIBLE);
        playAgainButton.setVisibility(View.VISIBLE);
    }

    //function to start the countdown
    private void startCountDown() {
        generateQuestion();

        timesUp = MediaPlayer.create(this, R.raw.timesup);
        tickSFX = MediaPlayer.create(this, R.raw.ticktock);

        countdown = new CountDownTimer(60200, 1000) {
            @Override
            public void onTick(long l) {
                int seconds = (int) (l / 1000);
                timeLeft.setText(String.format(Locale.getDefault(), "%02dsec", seconds));

                if (l <= 10200) {
                    tickSFX.start();
                    tickSFX.setVolume(30, 30);
                }
            }

            @Override
            public void onFinish() {
                timesUp.start();
                gameOver();
            }
        }.start();
    }

    public void playAgain(View view) {
        score = 0;
        questionCount = 0;
        answer = 0;
        String index = "1/10";

        startCountDown();

        questionIndex.setText(index);
        playAgainButton.setVisibility(View.GONE);
        scoreBoard.setVisibility(View.GONE);
        questionText.setVisibility(View.GONE);
        gridLayout.setVisibility(View.VISIBLE);
        questionText.setVisibility(View.VISIBLE);
    }

    public void checkAnswer(View view) {
        Button button = (Button) view;

        //if button->value equals to the string value of answer increase score & play sound
        if ((button.getText()).equals(String.valueOf(answer))) {
            rightAnswer = MediaPlayer.create(this, R.raw.right);
            rightAnswer.start();
            score += 10;
        } else {
            wrongAnswer = MediaPlayer.create(this, R.raw.wrong);
            wrongAnswer.start();
        }

        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            Button b = (Button) gridLayout.getChildAt(i);
            b.setClickable(false);
        }

        if (questionCount < 10)
            generateQuestion();
        else {
            gameOver();
        }

        new Handler().postDelayed(() -> {
            for (int i = 0; i < gridLayout.getChildCount(); i++) {
                Button b = (Button) gridLayout.getChildAt(i);
                b.setClickable(true);
            }
        }, 400);
    }

    public void startGame(View view) {
        titleText.setVisibility(View.GONE);
        titleLogo.setVisibility(View.GONE);
        startButton.setVisibility(View.GONE);

        constraintLayout.setVisibility(View.VISIBLE);
        gameLogo.setVisibility(View.VISIBLE);
        timeLeft.setVisibility(View.VISIBLE);
        questionIndex.setVisibility(View.VISIBLE);

        startCountDown();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.startButton);
        titleText = findViewById(R.id.titleText);
        titleLogo = findViewById(R.id.titleLogo);
        gameLogo = findViewById(R.id.gameLogo);
        timeLeft = findViewById(R.id.timeLeft);
        questionIndex = findViewById(R.id.questionIndex);
        questionText = findViewById(R.id.questionText);
        constraintLayout = findViewById(R.id.constraintLayout);
        gridLayout = findViewById(R.id.gridLayout);
        playAgainButton = findViewById(R.id.playAgain);
        scoreBoard = findViewById(R.id.scoreBoard);
    }
}