package com.example.trueorfalse;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private TextView questionTV;
    private Button trueButton;
    private Button falseButton;

    private int mQuestionIndex;
    private int score;
    private final QuizModel[] questionCollection = new QuizModel[]{
            new QuizModel(R.string.q1, false),
            new QuizModel(R.string.q2, false),
            new QuizModel(R.string.q3, true),
            new QuizModel(R.string.q4, false),
            new QuizModel(R.string.q5, true),
            new QuizModel(R.string.q6, true),
            new QuizModel(R.string.q7, false),
            new QuizModel(R.string.q8, false),
            new QuizModel(R.string.q9, true),
            new QuizModel(R.string.q10, true)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        questionTV = findViewById(R.id.questionTV);
        trueButton = findViewById(R.id.trueButton);
        falseButton = findViewById(R.id.falseButton);

        progressBar.setMax(10);
        if (savedInstanceState != null) {
            mQuestionIndex = savedInstanceState.getInt("question_index");
        }
        progressBar.setProgress(mQuestionIndex);
        QuizModel q1 = questionCollection[mQuestionIndex];
        questionTV.setText(q1.getQuestion());

        View.OnClickListener onClickListener = v -> {
            if (mQuestionIndex < 10) {
                boolean userAnswer = v.getId() == R.id.trueButton;
                checkAnswer(mQuestionIndex, userAnswer);
            }

            mQuestionIndex++;
            progressBar.setProgress(mQuestionIndex);

            if (mQuestionIndex < 10) {
                questionTV.setText(questionCollection[mQuestionIndex].getQuestion());
            } else {
                String scoreTxt = String.format(Locale.getDefault(), "Score: %d", score);
                questionTV.setText(scoreTxt);
                trueButton.setClickable(false);
                falseButton.setClickable(false);
                showAlertDialog();
            }
        };
        trueButton.setOnClickListener(onClickListener);
        falseButton.setOnClickListener(onClickListener);
    }

    private void resetGame() {
        Toast.makeText(this, "game reset", Toast.LENGTH_SHORT).show();
        mQuestionIndex = 0;
        progressBar.setProgress(mQuestionIndex);
        questionTV.setText(questionCollection[mQuestionIndex].getQuestion());
        trueButton.setClickable(true);
        falseButton.setClickable(true);
    }

    private void checkAnswer(int qNo, boolean userAnswer) {
        if (userAnswer == questionCollection[qNo].getAnswer()) {
            score += 100;
            Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Wrong", Toast.LENGTH_SHORT).show();
            score -= 10;
        }
    }

    private void showAlertDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Play Again")
                .setMessage("Do you want to play the quiz again?")
                .setPositiveButton(R.string.yes, (dialog, which) -> resetGame())
                .setNegativeButton(R.string.no, (dialog, which) -> finish())
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("question_index", mQuestionIndex);
    }
}