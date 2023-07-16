package com.example.jokesapp.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JokeManager {
    Context context;
    SharedPreferences sharedPreferences;

    public JokeManager(Context context) {
        this.context = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void saveJoke(Joke joke) {
        sharedPreferences.edit().putBoolean(joke.getJoke(), joke.isLiked()).apply();
    }

    public void removeJoke(Joke joke) {
        sharedPreferences.edit().remove(joke.getJoke()).commit();
    }

    public List<Joke> retrieveJokes() {
        List<Joke> jokes = new ArrayList<>();
        Map<String, ?> data = sharedPreferences.getAll();
        for (Map.Entry<String, ?> entry : data.entrySet()) {
            if (entry.getKey().matches("variations_seed_native_stored")) {
                continue;
            }
            jokes.add(new Joke(entry.getKey(), (boolean) entry.getValue()));
        }
        return jokes;
    }
}
