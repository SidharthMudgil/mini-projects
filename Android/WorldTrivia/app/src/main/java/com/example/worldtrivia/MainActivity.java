package com.example.worldtrivia;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.worldtrivia.model.QuizManager;

public class MainActivity extends AppCompatActivity {
    private QuizManager quizManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quizManager = new QuizManager(this);
        quizManager.getQuestions(this);
    }
}