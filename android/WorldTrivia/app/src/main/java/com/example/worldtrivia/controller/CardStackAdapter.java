package com.example.worldtrivia.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.worldtrivia.R;
import com.example.worldtrivia.model.QuizQuestion;
import com.example.worldtrivia.view.CardViewHolder;

import java.util.List;

public class CardStackAdapter extends RecyclerView.Adapter<CardViewHolder> {
    private Context context;
    private List<QuizQuestion> quizQuestions;
    private LayoutInflater layoutInflater;

    public CardStackAdapter(Context context, List<QuizQuestion> questions) {
        this.context = context;
        this.quizQuestions = questions;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.card_view, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        holder.getQuestionTV().setText(quizQuestions.get(position).getQuestionText());

        View.OnClickListener onClickListener = v -> {
            if (quizQuestions.get(position).isTrueAnswer()) {
                Toast.makeText(context, "Your answer is correct", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Ooops wrong answer", Toast.LENGTH_SHORT).show();
            }
        };
        holder.getTrueButton().setOnClickListener(onClickListener);
        holder.getFalseButton().setOnClickListener(onClickListener);
    }

    @Override
    public int getItemCount() {
        return quizQuestions.size();
    }
}
