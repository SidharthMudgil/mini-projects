package com.example.jokesapp.controller;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jokesapp.R;
import com.example.jokesapp.model.Joke;
import com.example.jokesapp.view.FavJokeViewHolder;

import java.util.List;

public class FavJokeAdapter extends RecyclerView.Adapter<FavJokeViewHolder> {
    private List<Joke> jokeList;
    private Context context;

    public FavJokeAdapter(List<Joke> jokeList, Context context) {
        this.jokeList = jokeList;
        this.context = context;
    }

    @NonNull
    @Override
    public FavJokeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fav_joke_item, parent, false);
        return new FavJokeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavJokeViewHolder holder, int position) {
        String jokeTxt = jokeList.get(position).getJoke();
        holder.getFavJokeTV().setText(jokeTxt);

        holder.getShareBtn().setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_SUBJECT, "Mama Joke!");
            intent.putExtra(Intent.EXTRA_TEXT, jokeTxt);
            context.startActivity(Intent.createChooser(intent, "Share Via"));
        });
    }

    @Override
    public int getItemCount() {
        return jokeList.size();
    }
}
