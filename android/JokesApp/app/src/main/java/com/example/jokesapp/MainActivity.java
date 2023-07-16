package com.example.jokesapp;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.arasthel.asyncjob.AsyncJob;
import com.example.jokesapp.controller.CardsDataAdapter;
import com.example.jokesapp.controller.JokeLikeListener;
import com.example.jokesapp.model.Joke;
import com.example.jokesapp.model.JokeManager;
import com.github.tbouron.shakedetector.library.ShakeDetector;
import com.wenchao.cardstack.CardStack;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements JokeLikeListener {
    ArrayList<Joke> jokesArray;
    private JokeManager jokeManager;
    final ArrayList<String> keywords = new ArrayList<>(Arrays.asList(
            "fat",
            "stupid",
            "ugly",
            "nasty",
            "hairy",
            "bald",
            "old",
            "poor",
            "short",
            "skinny",
            "tall",
            "like"
    ));
    private CardStack cardStack;
    private CardsDataAdapter cardsDataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jokeManager = new JokeManager(this);
        jokesArray = new ArrayList<>();

        cardStack = findViewById(R.id.container);
        cardStack.setContentResource(R.layout.card_view);
        cardStack.setStackMargin(20);
        cardsDataAdapter = new CardsDataAdapter(this, 0);

        new AsyncJob.AsyncJobBuilder<Boolean>()
                .doInBackground(() -> {
                    try {
                        JSONObject json = getJsonFromAssets(this);
                        for (String keyword : keywords) {
                            JSONArray jsonArray = getJSONArray(Objects.requireNonNull(json), keyword);
                            addJokesToArrayList(jsonArray, jokesArray);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return true;
                })
                .doWhenFinished((AsyncJob.AsyncResultAction<Boolean>) result -> {
                    for (int i = 0; i < jokesArray.size(); i++) {
                        cardsDataAdapter.add(jokesArray.get(i).getJoke());
                    }
                    cardStack.setAdapter(cardsDataAdapter);
                }).create().start();

        ShakeDetector.create(this, this::handleShakeEvent);
    }

    private void handleShakeEvent() {
        new AsyncJob.AsyncJobBuilder<Boolean>().doInBackground(() -> {
            Collections.shuffle(jokesArray);
            return true;
        }).doWhenFinished(o -> {
            cardsDataAdapter.clear();
            cardsDataAdapter = new CardsDataAdapter(MainActivity.this, 0);
            for (Joke joke : jokesArray) {
                cardsDataAdapter.add(joke.getJoke());
            }
            cardStack.setAdapter(cardsDataAdapter);
        }).create().start();
    }

    private JSONObject getJsonFromAssets(Context context) {
        try {
            InputStream inputStream = context.getAssets().open("jokes.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String jsonString = new String(buffer, StandardCharsets.UTF_8);
            return new JSONObject(jsonString);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private JSONArray getJSONArray(JSONObject json, String keyword) {
        try {
            return json.getJSONArray(keyword);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void addJokesToArrayList(JSONArray jsonArray, List<Joke> arrayList) {
        if (jsonArray != null) {
            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    arrayList.add(new Joke(jsonArray.getString(i), false));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void jokeIsLiked(Joke joke) {
        if (joke.isLiked()) {
            jokeManager.saveJoke(joke);
        } else {
            jokeManager.removeJoke(joke);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.favJokes) {
            startActivity(new Intent(MainActivity.this, FavJokesActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ShakeDetector.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        ShakeDetector.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ShakeDetector.destroy();
    }
}