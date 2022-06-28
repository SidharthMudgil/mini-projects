package com.example.memepur.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.arasthel.asyncjob.AsyncJob;
import com.example.memepur.utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CategoryManager {
    public interface CategoryUpdateListener {
        void updateRecyclerView(RecyclerView recyclerView, ArrayList<Category> categoryArrayList);
    }

    private final Context context;
    private ArrayList<Category> categoryArrayList;
    private final RecyclerView recyclerView;
    public CategoryUpdateListener categoryUpdateListener;

    public CategoryManager(Context context, RecyclerView recyclerView, CategoryUpdateListener categoryUpdateListener) {
        this.context = context;
        categoryArrayList = new ArrayList<>();
        this.recyclerView = recyclerView;
        this.categoryUpdateListener = categoryUpdateListener;
    }

    private ArrayList<Category> retrieveFromAssets() {
        new AsyncJob.AsyncJobBuilder<Boolean>()
                .doInBackground(() -> {
                    try {
                        JSONArray jsonArray = Utils.loadJSONFromAsset(context);
                        if (jsonArray != null) {
                            categoryArrayList = Utils.getCategoryListFromJSONArray(context, jsonArray);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return true;
                })
                .doWhenFinished(o -> {
                    if (categoryArrayList == null) {
                        throw new RuntimeException("Array List Empty");
                    } else {
                        categoryUpdateListener.updateRecyclerView(recyclerView, categoryArrayList);
                    }
                }).create().start();

        return categoryArrayList;
    }

    private ArrayList<Category> retrieveFromPreferences(String jsonString) {
        new AsyncJob.AsyncJobBuilder<Boolean>()
                .doInBackground(() -> {
                    try {
                        JSONArray jsonArray = new JSONArray(jsonString);
                        categoryArrayList = Utils.getCategoryListFromJSONArray(jsonArray);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return true;
                })
                .doWhenFinished(o -> {
                    if (categoryArrayList == null) {
                        throw new RuntimeException("Array List Empty");
                    } else {
                        categoryUpdateListener.updateRecyclerView(recyclerView, categoryArrayList);
                    }
                }).create().start();

        return categoryArrayList;
    }

    public ArrayList<Category> retrieveCategoryList() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String jsonString = sharedPreferences.getString(Utils.DATA_KEY, Utils.DEFAULT_VALUE);

        if (jsonString.equalsIgnoreCase(Utils.DEFAULT_VALUE)) {
            Log.d("retrieveState", "FIRST INSTALL");
            return retrieveFromAssets();
        } else {
            Log.d("retrieveState", "OLD DEVICE");
            return retrieveFromPreferences(jsonString);
        }
    }

    public void addNewCategory(Category category) {
        categoryArrayList.add(category);
        categoryUpdateListener.updateRecyclerView(recyclerView, categoryArrayList);
    }

    public static void updateCategory(Category category, Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String jsonString = sharedPreferences.getString(Utils.DATA_KEY, Utils.DEFAULT_VALUE);

        if (!jsonString.equalsIgnoreCase(Utils.DEFAULT_VALUE)) {
            try {
                JSONArray data = new JSONArray(jsonString);

                if (category.getJokeList().size() < 1) {
                    removeEmptyCategory(context);
                } else {
                    JSONObject categoryObject = new JSONObject();
                    categoryObject.put("category", category.getCategoryName());
                    categoryObject.put("jokes", getJokeJSONArray(category.getJokeList()));

                    data.put(categoryObject);

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.clear();
                    editor.putString(Utils.DATA_KEY, data.toString());
                    editor.apply();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            throw new RuntimeException("Array List Empty");
        }
    }

    private static JSONArray getJokeJSONArray(ArrayList<Joke> jokeArrayList) {
        JSONArray jsonList = new JSONArray();

        for (int i = 0; i < jokeArrayList.size(); i++) {
            Joke joke = jokeArrayList.get(i);
            JSONObject jokeObject = new JSONObject();

            try {
                jokeObject.put("body", joke.getJokeBody());
                jokeObject.put("rating", joke.getJokeRating());

                jsonList.put(jokeObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return jsonList;
    }

    private static void removeEmptyCategory(Context context) {
        Toast.makeText(context, "How the fuck i remove this", Toast.LENGTH_SHORT).show();
    }
}
