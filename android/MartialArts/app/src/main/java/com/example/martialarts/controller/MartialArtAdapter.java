package com.example.martialarts.controller;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.martialarts.R;
import com.example.martialarts.model.MartialArt;
import com.example.martialarts.view.MartialArtHolder;

import java.util.ArrayList;

public class MartialArtAdapter extends RecyclerView.Adapter<MartialArtHolder> {
    public interface MartialArtUpdateListener {
        void updateMartialArt(int id);
    }

    private final ArrayList<MartialArt> martialArts;
    private final MartialArtUpdateListener martialArtUpdateListener;

    public MartialArtAdapter(ArrayList<MartialArt> martialArts, MartialArtUpdateListener martialArtUpdateListener) {
        this.martialArts = martialArts;
        this.martialArtUpdateListener = martialArtUpdateListener;
    }

    @NonNull
    @Override
    public MartialArtHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.martial_art, parent, false);
        return new MartialArtHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MartialArtHolder holder, int position) {
        MartialArt martialArt = martialArts.get(position);

        holder.getNameTV().setText(martialArt.getMartialArtName());
        holder.getColorTV().setText(String.format("color: %s", martialArt.getMartialArtColor()));
        holder.getPriceTV().setText(String.valueOf(martialArt.getMartialArtPrice()));

        holder.getMaterialCard().setOnLongClickListener(v -> {
            martialArtUpdateListener.updateMartialArt(position);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return martialArts.size();
    }
}
