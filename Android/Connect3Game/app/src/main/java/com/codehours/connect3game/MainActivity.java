package com.codehours.connect3game;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    //yellow = 0, red = 1
    int player = 0;
    int[] gameState = {-1, -1, -1, -1, -1, -1, -1, -1, -1};
    int[][] winningState = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    boolean gameOver = false;

    public void dropIn(View view) {
        ImageView boxCount = (ImageView) view;
        int boxNo = Integer.parseInt(boxCount.getTag().toString());

        if (gameState[boxNo] == -1 && !gameOver) {
            gameState[boxNo] = player;
            boxCount.setTranslationY(-1500);

            if (player == 0) {
                boxCount.setImageResource(R.drawable.yellow);
                player = 1;
            } else {
                boxCount.setImageResource(R.drawable.red);
                player = 0;
            }
            boxCount.animate().translationYBy(1500).rotation(360).setDuration(300);

            for (int[] winPos : winningState) {
                if (gameState[winPos[0]] == gameState[winPos[1]] && gameState[winPos[1]] == gameState[winPos[2]] && gameState[winPos[0]] != -1) {
                    TextView textView = (TextView) findViewById(R.id.textView);
                    String winner;

                    if (player == 0) {
                        winner = "red";
                        textView.setTextColor(Color.RED);
                        textView.setBackgroundColor(Color.YELLOW);
                    } else {
                        winner = "yellow";
                        textView.setTextColor(Color.YELLOW);
                        textView.setBackgroundColor(Color.RED);
                    }

                    Button button = (Button) findViewById(R.id.button);
                    button.setVisibility(View.VISIBLE);
                    textView.setVisibility(View.VISIBLE);

                    textView.setText(winner + " won");
                    textView.animate().scaleXBy(3).scaleYBy(3).setDuration(400);

                    gameOver = true;
                }
            }
        }
        {
            gameOver = true;
            for (int i : gameState) {
                System.out.print(i + " ");
                if (i == -1) {
                    gameOver = false;
                    break;
                }
            }
            System.out.println();
            if (gameOver) {
                TextView textView = (TextView) findViewById(R.id.textView);
                Button button = (Button) findViewById(R.id.button);

                button.setVisibility(View.VISIBLE);
                textView.setTextColor(Color.BLACK);
                textView.setBackgroundColor(Color.LTGRAY);
                textView.setVisibility(View.VISIBLE);

                textView.setText("draw");
                textView.animate().scaleXBy(3).scaleYBy(3).setDuration(400);
            }
        }
    }


    public void tryAgain(View view) {
        Button button = (Button) findViewById(R.id.button);
        TextView textView = findViewById(R.id.textView);

        button.setVisibility(View.GONE);
        textView.setVisibility(View.GONE);

        player = 0;
        gameOver = false;
        textView.setScaleX(0);
        textView.setScaleY(0);
        Arrays.fill(gameState, -1);

        androidx.gridlayout.widget.GridLayout gridLayout = (androidx.gridlayout.widget.GridLayout) findViewById(R.id.gridLayout);
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ImageView imageView = (ImageView) gridLayout.getChildAt(i);
            imageView.setImageDrawable(null);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}