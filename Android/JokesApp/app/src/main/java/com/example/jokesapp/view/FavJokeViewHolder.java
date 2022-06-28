package com.example.jokesapp.view;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jokesapp.R;

public class FavJokeViewHolder extends RecyclerView.ViewHolder {
    private TextView favJokeTV;
    private ImageButton shareBtn;

    public FavJokeViewHolder(@NonNull View itemView) {
        super(itemView);

        favJokeTV = itemView.findViewById(R.id.favJokeTV);
        shareBtn = itemView.findViewById(R.id.favShareButton);
    }

    public TextView getFavJokeTV() {
        return favJokeTV;
    }

    public ImageButton getShareBtn() {
        return shareBtn;
    }
}
