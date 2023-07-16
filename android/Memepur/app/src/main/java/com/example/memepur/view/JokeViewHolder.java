package com.example.memepur.view;

import android.text.method.ScrollingMovementMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.memepur.R;

public class JokeViewHolder extends RecyclerView.ViewHolder {
    private final TextView jokeBodyTV;
    private final ScrollView childScroll;

    public JokeViewHolder(@NonNull View itemView) {
        super(itemView);

        jokeBodyTV = itemView.findViewById(R.id.joke_body);
        childScroll = itemView.findViewById(R.id.childScroll);
    }

    public TextView getJokeBodyTV() {
        return jokeBodyTV;
    }

    public ScrollView getChildScroll() {
        return childScroll;
    }
}
