package com.example.memepur.controller;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.memepur.R;
import com.example.memepur.model.Category;
import com.example.memepur.model.Joke;
import com.example.memepur.view.JokeViewHolder;

import java.util.ArrayList;

public class JokeAdapter extends RecyclerView.Adapter<JokeViewHolder> {
    private final ArrayList<Joke> jokeList;

    public JokeAdapter(Category category) {
        jokeList = category.getJokeList();
    }

    @NonNull
    @Override
    public JokeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.joke_view_holder, parent, false);
        return new JokeViewHolder(view);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(@NonNull JokeViewHolder holder, int position) {
        holder.getJokeBodyTV().setText(jokeList.get(position).getJokeBody());

        holder.getChildScroll().setOnTouchListener((View.OnTouchListener) (v, event) -> {
            // Disallow the touch request for parent scroll on touch of child view
            v.getParent().requestDisallowInterceptTouchEvent(true);
            return false;
        });
    }

    @Override
    public int getItemCount() {
        return jokeList.size();
    }
}
