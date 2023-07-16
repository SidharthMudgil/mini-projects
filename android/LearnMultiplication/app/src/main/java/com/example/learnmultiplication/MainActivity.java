package com.example.learnmultiplication;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private LottieAnimationView animationView;

    private TextToSpeech tts;

    private TextView questionTV;
    private TextView answerTV;

    private int correctAnswer = 0;

    ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                Intent data = result.getData();
                String answer = "";
                if (data != null) {
                    ArrayList<String> re = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    answer = Objects.requireNonNull(re).get(0);
                }
                check(answer, correctAnswer);
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionTV = findViewById(R.id.questionTV);
        answerTV = findViewById(R.id.answerTV);

        animationView = findViewById(R.id.animationView);

        tts = new TextToSpeech(this, i -> {
            tts.setPitch(0.7f);
            tts.setSpeechRate(0.7f);

            tts.setOnUtteranceProgressListener(new UtteranceProgressListener() {
                @Override
                public void onStart(String s) {
                    runOnUiThread(() -> {
                        animationView.setVisibility(View.VISIBLE);
                        answerTV.setVisibility(View.GONE);
                    });
                }

                @Override
                public void onDone(String s) {
                    runOnUiThread(() -> {
                        animationView.setVisibility(View.GONE);
                        answerTV.setVisibility(View.VISIBLE);
                    });

                    if (s.equalsIgnoreCase("question_tts")) {
                        listen();
                    }
                }

                @Override
                public void onError(String s) {

                }
            });

            int lang = tts.setLanguage(Locale.US);
            if (lang == TextToSpeech.LANG_MISSING_DATA || lang == TextToSpeech.LANG_NOT_SUPPORTED)
                Log.d("speech", "Language Not Supported");
            else
                Log.d("speech", "Language Supported");

            play();
        });

        questionTV.setOnClickListener(view -> play());
    }

    private void listen() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak to text");
        launcher.launch(intent);
    }

    private void check(String userAnswer, int correctAnswer) {
        int answer;
        try {
            answer = Integer.parseInt(userAnswer);
        } catch (Exception e) {
            answer = 0;
        }
        if (answer == correctAnswer) {
            speak("Correct Answer", "result_tts");
            answerTV.setText(userAnswer);
            answerTV.setTextColor(getResources().getColor(R.color.green_400));
        } else {
            speak("Incorrect Answer", "result_tts");
            answerTV.setText(userAnswer);
            answerTV.setTextColor(getResources().getColor(R.color.red_400));
        }
    }

    private void play() {
        int a, b;
        a = getNumber();
        b = getNumber();

        correctAnswer = a * b;

        String speech = String.format(Locale.US, "%d times %d is?", a, b);
        String text = String.format(Locale.US, "%d x %d ?", a, b);
        questionTV.setText(text);
        speak(speech, "question_tts");
    }

    private int getNumber() {
        return new Random().nextInt(10) + 1;
    }

    private void speak(String speech, String id) {
        tts.speak(speech, TextToSpeech.QUEUE_FLUSH, null, id);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (tts != null) {
            tts.stop();
        }
    }
}
