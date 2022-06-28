package com.example.fastfingers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.infoItem) {
            Toast.makeText(this, BuildConfig.VERSION_NAME, Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    int total_count;
    int total_time;
    boolean firstGame;

    TextView timer;
    TextView counter;
    Button tapButton;

    CountDownTimer countDownTimer;

    private void resetGame() {
        countDownTimer.cancel();

        firstGame = true;
        total_time = 15;
        total_count = 100;
        String buttonText = "Tap Here!";
        String counterText = "100 more to go";
        String timerText = "15 sec";

        counter.setText(counterText);
        timer.setText(timerText);
        tapButton.setText(buttonText);

        tapButton.setClickable(true);
    }

    private void showAlertDialog(String message) {
        new AlertDialog.Builder(this)
                .setTitle("Try again")
                .setMessage(message)
                .setPositiveButton("Yes", (dialog, which) -> resetGame())
                .setNegativeButton("No", null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void startTimer() {
        Toast.makeText(this, R.string.game_started, Toast.LENGTH_SHORT).show();
        countDownTimer.start();
    }

    private void updateCounter() {
        String counterText = "";
        if (total_count > 50) {
            counterText = String.format(Locale.getDefault(), "%02d more to go", total_count);
        } else if (total_count == 50) {
            counterText = "Half way completed";
        } else if (total_count >= 30) {
            counterText = String.format(Locale.getDefault(), "%02d more, FAST!!!", total_count);
        } else if (total_count >= 10) {
            counterText = String.format(Locale.getDefault(), "%02d MOOORrrEE", total_count);
        } else if (total_count > 0) {
            counterText = String.format(Locale.getDefault(), "%02d PleasZZee faast", total_count);
        } else if (total_count == 0) {
            counterText = "Aaaahhaahha";
            tapButton.setClickable(false);
            String buttonText = "WooohOOO";
            tapButton.setText(buttonText);
            showAlertDialog("Wanna play again");
        }
        counter.setText(counterText);
    }

    private void restoreGame() {
        updateCounter();
        String buttonText = "Tap Here!";
        String timerText = total_time + " sec";

        tapButton.setText(buttonText);
        timer.setText(timerText);

        countDownTimer = new CountDownTimer(total_time * 1000L, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                total_time--;
                String timerText = String.format(Locale.getDefault(), "%02d sec", total_time);
                if (total_time >= 0) {
                    timer.setText(timerText);
                }
            }

            @Override
            public void onFinish() {
                tapButton.setClickable(false);
                if (total_count > 0) {
                    String buttonText = "Haha Looser";
                    tapButton.setText(buttonText);
                    showAlertDialog("Do you want to try again");
                }
            }
        }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        total_count = 99;
        total_time = 15;
        firstGame = true;

        timer = findViewById(R.id.timerTV);
        counter = findViewById(R.id.tapCountTV);
        tapButton = findViewById(R.id.button);

        if (savedInstanceState != null) {
            total_count = savedInstanceState.getInt("total_count");
            total_time = savedInstanceState.getInt("total_time");
            firstGame = savedInstanceState.getBoolean("first_game");

            restoreGame();
        }

        tapButton.setOnClickListener(v -> {
            updateCounter();
            if (firstGame) {
                firstGame = false;
                startTimer();
            }
            total_count--;
        });

        countDownTimer = new CountDownTimer(15000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                total_time--;
                String timerText = String.format(Locale.getDefault(), "%02d sec", total_time);
                if (total_time >= 0) {
                    timer.setText(timerText);
                }
            }

            @Override
            public void onFinish() {
                tapButton.setClickable(false);
                if (total_count > 0) {
                    String buttonText = "Haha Looser";
                    tapButton.setText(buttonText);
                    showAlertDialog("Do you want to try again");
                }
            }
        };
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("total_time", total_time);
        outState.putInt("total_count", total_count);
        outState.putBoolean("first_game", firstGame);
        countDownTimer.cancel();
    }
}