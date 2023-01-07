package com.sidharth.home.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sidharth.songslikethis.R;
import com.sidharth.home.model.TrackInfo;
import com.sidharth.home.view.PopularSongsViewModel;

import java.util.ArrayList;

public class PopularSongsAdapter extends RecyclerView.Adapter<PopularSongsViewModel> {
    public interface PopularSongClickListener {
        void onClick(TrackInfo trackInfo);
    }

    private final PopularSongClickListener clickListener;
    private final Context context;
    private final ArrayList<TrackInfo> popularSongs;

    public PopularSongsAdapter(Context context, ArrayList<TrackInfo> popularSongs, PopularSongClickListener clickListener) {
        this.context = context;
        this.popularSongs = popularSongs;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public PopularSongsViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_popular, parent, false);
        return new PopularSongsViewModel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularSongsViewModel holder, int position) {
        holder.bind(context, popularSongs.get(position));
        holder.getCardView().setOnClickListener(view -> clickListener.onClick(popularSongs.get(position)));
    }

    @Override
    public int getItemCount() {
        return popularSongs.size();
    }
}
