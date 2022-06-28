package com.example.memepur.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.memepur.model.Category;
import com.example.memepur.model.Joke;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Utils {
    public static final String DATA_KEY = "categories@memepur";
    public static final String DEFAULT_VALUE = "null@memepur";

    static public JSONArray loadJSONFromAsset(Context context) {
        String json;

        try {
            InputStream inputStream = context.getAssets().open("data.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, StandardCharsets.UTF_8);

            return new JSONArray(json);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    static public ArrayList<Category> getCategoryListFromJSONArray(JSONArray data) {
        ArrayList<Category> categoryArrayList = new ArrayList<>();

        for (int i = 0; i < data.length(); i++) {
            try {
                JSONObject jsonObject = data.getJSONObject(i);
                String categoryName = jsonObject.getString("category");
                JSONArray jokes = jsonObject.getJSONArray("jokes");

                ArrayList<Joke> jokeArrayList = getJokeList(jokes);
                Category category = new Category(categoryName, jokeArrayList);
                categoryArrayList.add(category);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return categoryArrayList;
    }

    static public ArrayList<Category> getCategoryListFromJSONArray(Context context, JSONArray data) {
        ArrayList<Category> categoryArrayList = new ArrayList<>();

        for (int i = 0; i < data.length(); i++) {
            try {
                JSONObject jsonObject = data.getJSONObject(i);
                String categoryName = jsonObject.getString("category");
                JSONArray jokes = jsonObject.getJSONArray("jokes");

                ArrayList<Joke> jokeArrayList = getJokeList(jokes);
                Category category = new Category(categoryName, jokeArrayList);
                categoryArrayList.add(category);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(DATA_KEY, data.toString());
        editor.apply();

        return categoryArrayList;
    }

    private static ArrayList<Joke> getJokeList(JSONArray data) {
        ArrayList<Joke> jokeArrayList = new ArrayList<>();

        for (int i = 0; i < data.length(); i++) {
            try {
                JSONObject jsonObject = data.getJSONObject(i);
                String jokeBody = jsonObject.getString("body");
                double jokeRating = jsonObject.getDouble("rating");

                Joke joke = new Joke(jokeBody, jokeRating);
                jokeArrayList.add(joke);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return jokeArrayList;
    }
}
