package com.sidharth.home.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sidharth.songslikethis.R;
import com.sidharth.home.model.TrackInfo;
import com.sidharth.home.view.SimilarSongsViewHolder;

import java.util.ArrayList;

public class SimilarSongsAdapter extends RecyclerView.Adapter<SimilarSongsViewHolder> {
    public interface SimilarSongClickListener {
        void onClick(TrackInfo trackInfo);
    }

    private final SimilarSongClickListener clickListener;
    private final ArrayList<TrackInfo> similarSongs;
    private final Context context;

    public SimilarSongsAdapter(Context context, ArrayList<TrackInfo> similarSongs, SimilarSongClickListener clickListener) {
        this.context = context;
        this.similarSongs = similarSongs;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public SimilarSongsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_similar, parent, false);
        return new SimilarSongsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SimilarSongsViewHolder holder, int position) {
        holder.bind(context, similarSongs.get(position));
        holder.getCardView().setOnClickListener(view -> clickListener.onClick(similarSongs.get(position)));
    }

    @Override
    public int getItemCount() {
        return similarSongs.size();
    }
}
