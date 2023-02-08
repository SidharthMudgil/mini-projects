package com.sidharth.math4kid;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionViewHolder> {
    private final ArrayList<ArrayList<Integer>> questions;
    private final int operation;
    private final int format;

    public QuestionAdapter(ArrayList<ArrayList<Integer>> questions, int operation, int format) {
        this.questions = questions;
        this.operation = operation;
        this.format = format;
    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (format == 0) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_question_v, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_question_h, parent, false);
        }
        return new QuestionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {
        char opr;

        if (operation == 0) {
            opr = '+';
        } else if (operation == 1) {
            opr = '-';
        } else {
            opr = 'x';
        }

        holder.bind(questions.get(position).get(0), questions.get(position).get(1), opr);
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }
}