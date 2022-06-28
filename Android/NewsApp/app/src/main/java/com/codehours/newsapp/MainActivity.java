package com.codehours.newsapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static ArrayList<String> urls;
    static ArrayList<String> titles;

    static ArrayAdapter<String> arrayAdapter;

    SQLiteDatabase newsDatabase;
    SharedPreferences sharedPreferences;

    private void openNews(int nNo) {
        Intent intent = new Intent(this, OpenNewsActivity.class);
        intent.putExtra("url", urls.get(nNo));
        startActivity(intent);
    }

    //return true if either wifi or data is connected
    private boolean isDataConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }

    //make toast if there's an error in volley request
    private void makeErrorToast(VolleyError error) {
        Log.i("ERROR", error.getMessage());
        Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
    }

    //make JsonObjectRequest to get properties of story
    private void makeJsonObjectRequest(RequestQueue requestQueue, String url) {
        JsonObjectRequest objectRequest = new JsonObjectRequest(
                Request.Method.GET, url, null,
                response -> {
                    Log.i("Result", response.toString());

                    try {
                        String query = "INSERT INTO news(ID, AUTHOR, TITLE, URL) VALUES(?, ?, ?, ?)";
                        SQLiteStatement sqLiteStatement = newsDatabase.compileStatement(query);
                        sqLiteStatement.bindString(1, response.getString("id"));
                        sqLiteStatement.bindString(2, response.getString("by"));
                        sqLiteStatement.bindString(3, response.getString("title"));
                        sqLiteStatement.bindString(4, response.getString("url"));

                        sqLiteStatement.execute();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    updateListView();
                },
                this::makeErrorToast
        );
        requestQueue.add(objectRequest);
    }

    //make request to get all the New, Top and Best Stories
    private void doInBackground() {
        //url to New, Top and Best Stories
        String url = "https://hacker-news.firebaseio.com/v0/topstories.json?print=pretty";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest arrayRequest = new JsonArrayRequest(
                Request.Method.GET, url, null,
                response -> {
                    newsDatabase.execSQL("DELETE FROM news");

                    for (int i = 0; i < response.length(); i++) {
                        try {
                            //url to stories properties
                            String address = "https://hacker-news.firebaseio.com/v0/item/" + response.getInt(i) + ".json?print=pretty";
                            makeJsonObjectRequest(requestQueue, address);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                this::makeErrorToast
        );
        requestQueue.add(arrayRequest);

        sharedPreferences.edit().putBoolean("dataStats", true).apply();
    }

    public void updateListView() {
        Cursor c = newsDatabase.rawQuery("SELECT * FROM news", null);

        int iUrl = c.getColumnIndex("URL");
        int iTitle = c.getColumnIndex("TITLE");

        if (c.moveToFirst()) {
            urls.clear();
            titles.clear();
            do {
                urls.add(c.getString(iUrl));
                titles.add(c.getString(iTitle));
            } while (c.moveToNext());
            arrayAdapter.notifyDataSetChanged();
        }
        c.close();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        urls = new ArrayList<>();
        titles = new ArrayList<>();

        sharedPreferences = this.getSharedPreferences("com.codehours.newsapp",MODE_PRIVATE);

        newsDatabase = this.openOrCreateDatabase("News", MODE_PRIVATE, null);
        newsDatabase.execSQL("CREATE TABLE IF NOT EXISTS news(" +
                "ID INTEGER PRIMARY KEY," +
                "AUTHOR VARCHAR DEFAULT 'Unknown'," +
                "TITLE VARCHAR NOT NULL," +
                "URL VARCHAR NOT NULL)");

        boolean isDataStored = sharedPreferences.getBoolean("dataStats", false);
        if (!isDataStored) {
            doInBackground();
        }

        ListView listView = findViewById(R.id.listView);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, titles) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView textView = view.findViewById(android.R.id.text1);
                textView.setTextColor(Color.parseColor("#458593"));
                textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                textView.setTextSize(28);
                return view;
            }
        };
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            if (isDataConnected()) {
                openNews(i);
            } else {
                Toast.makeText(this, "CONNECTION LOST", Toast.LENGTH_SHORT).show();
            }
        });
        updateListView();
    }
}