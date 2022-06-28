package com.example.worldtrivia.model;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.worldtrivia.R;
import com.example.worldtrivia.VolleySingleton;
import com.example.worldtrivia.controller.CardStackAdapter;
import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class QuizManager {
    private final RequestQueue requestQueue;
    private final String url;
    Context context;

    public QuizManager(Context context) {
        this.context = context;
        requestQueue = VolleySingleton.getInstance().getRequestQueue();
        url = "https://opentdb.com/api.php?amount=10&type=boolean";
    }

    public void getQuestions(Activity activity) {
        List<QuizQuestion> questions = new ArrayList<>();

        JsonObjectRequest objectRequest = new JsonObjectRequest(
                Request.Method.GET, url, null,
                response -> {
                    try {
                        JSONArray results = response.getJSONArray("results");
                        for (int i = 0; i < results.length(); i++) {
                            String question = results.getJSONObject(i).getString("question");
                            boolean answer = results.getJSONObject(i).getBoolean("correct_answer");
                            questions.add(new QuizQuestion(question, answer));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    CardStackView cardStackView = activity.findViewById(R.id.cardStackView);
                    cardStackView.setLayoutManager(new CardStackLayoutManager(context));
                    cardStackView.setAdapter(new CardStackAdapter(context, questions));
                },
                error -> Log.i("ERROR", error.getMessage()));
        requestQueue.add(objectRequest);
    }
}
