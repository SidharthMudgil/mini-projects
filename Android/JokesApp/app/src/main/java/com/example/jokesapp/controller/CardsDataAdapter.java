package com.example.jokesapp.controller;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.jokesapp.R;
import com.example.jokesapp.model.Joke;

public class CardsDataAdapter extends ArrayAdapter<String> {
    private final Context context;
    private boolean liked = true;

    private final JokeLikeListener jokeLikeListener;
    private SharedPreferences sharedPreferences;

    public CardsDataAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        this.context = context;
        jokeLikeListener = (JokeLikeListener) context;

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Override
    public View getView(int position, final View contentView, ViewGroup parent) {
        //supply the layout for your card
        TextView jokesTV = contentView.findViewById(R.id.content);
        jokesTV.setText(getItem(position));

        ImageButton likeButton = contentView.findViewById(R.id.likeButton);
        ImageButton shareButton = contentView.findViewById(R.id.shareButton);

        if (sharedPreferences.contains(getItem(position))) {
            likeButton.setImageResource(R.drawable.like_image);
            liked = true;
        } else {
            liked = false;
        }

        likeButton.setOnClickListener(v -> {
            if (liked) {
                likeButton.setImageResource(R.drawable.unlike_image);
                liked = false;
                jokeLikeListener.jokeIsLiked(new Joke(getItem(position), false));
            } else {
                likeButton.setImageResource(R.drawable.like_image);
                liked = true;
                YoYo.with(Techniques.Tada)
                        .duration(700)
                        .playOn(likeButton);
                jokeLikeListener.jokeIsLiked(new Joke(getItem(position), true));
            }
        });
        shareButton.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            String shareBody = jokesTV.getText().toString();
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_SUBJECT, "Mama Joke!");
            intent.putExtra(Intent.EXTRA_TEXT, shareBody);
            jokesTV.getContext().startActivity(Intent.createChooser(intent, "Share Via"));
        });
        return contentView;
    }

}
