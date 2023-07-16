package com.codehours.guessthecartoonapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    androidx.gridlayout.widget.GridLayout gridLayout;
    ProgressBar progressBar;

    Random random;

    ArrayList<String> imageURLs;
    ArrayList<String> character;

    int imageTracker;
    int correctAnswer;

    //class to get the webContent i.e HTML code using background thread <AsyncTask>
    class GetWebContent extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            StringBuilder webContent;
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = urlConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                webContent = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    webContent.append(line).append("\n");
                }

                return webContent.toString();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    //class to get the image Bitmap using background thread <AsyncTask>
    class GetImageBitmap extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = urlConnection.getInputStream();

                return BitmapFactory.decodeStream(inputStream);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    //method to add all the image urls and the character name to the arrayList
    protected void setGameData() {
        GetWebContent getWebContent = new GetWebContent();
        try {
            String content = getWebContent.execute("https://www.loudegg.com/most-famous-cartoon-characters/").get();

            //pattern and matcher for the image urls
            Pattern pattern1 = Pattern.compile("srcset=\"(.*?) ");
            Matcher matcher1 = pattern1.matcher(content);
            while (matcher1.find()) {
                imageURLs.add(matcher1.group(1));
            }

            for (String x : imageURLs) {
                Log.i("image url: ", x);
            }

            //pattern and matcher for the character name
            Pattern pattern2 = Pattern.compile("alt=\"(.*?)\"");
            Matcher matcher2 = pattern2.matcher(content);
            while (matcher2.find()) {
                character.add(matcher2.group(1));
            }

            for (String x : character) {
                Log.i("character: ", x);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //function to generate new questions
    private void newQuestion() {
        GetImageBitmap getImageBitmap = new GetImageBitmap();

        try {
            Bitmap bitmap = getImageBitmap.execute(imageURLs.get(imageTracker)).get();
            imageView.setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }

        correctAnswer = random.nextInt(4);

        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            Button btn = (Button) gridLayout.getChildAt(i);

            if (i == correctAnswer) {
                btn.setText(character.get(imageTracker));
            } else {
                btn.setText(character.get(random.nextInt(character.size())));
            }
        }
        imageTracker++;
    }

    //manages the game to play, do all the rest
    protected void startGame() {
        newQuestion();

        progressBar.setVisibility(View.GONE);
        imageView.setVisibility(View.VISIBLE);
        gridLayout.setVisibility(View.VISIBLE);
    }

    //function to check for the guess is right or wrong calls every time a option is selected
    public void checkAnswer(View view) {
        Button option = (Button) view;

        if (option.getText().equals(character.get(imageTracker - 1))) {
            Toast.makeText(this, "Right :)", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Wrong :(", Toast.LENGTH_SHORT).show();
        }

        newQuestion();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        gridLayout = findViewById(R.id.gridLayout);
        progressBar = findViewById(R.id.progress);

        random = new Random();

        imageURLs = new ArrayList<>();
        character = new ArrayList<>();

        imageTracker = 0;

        setGameData();
        startGame();
    }
}