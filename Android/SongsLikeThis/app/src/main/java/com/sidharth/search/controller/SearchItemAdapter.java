package com.sidharth.search.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.sidharth.songslikethis.R;
import com.sidharth.home.model.TrackInfo;
import com.sidharth.search.view.SearchItemViewHolder;

import java.util.ArrayList;

public class SearchItemAdapter extends RecyclerView.Adapter<SearchItemViewHolder> {
    public interface SearchResultOnClickListener {
        void onClick(TrackInfo trackInfo);
    }

    private final Context context;
    private final ArrayList<TrackInfo> results;
    private final SearchResultOnClickListener searchResultOnClickListener;

    public SearchItemAdapter(Context context, ArrayList<TrackInfo> results, SearchResultOnClickListener searchResultOnClickListener) {
        this.context = context;
        this.results = results;
        this.searchResultOnClickListener = searchResultOnClickListener;
    }

    @NonNull
    @Override
    public SearchItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search, parent, false);
        return new SearchItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchItemViewHolder holder, int position) {
        holder.bind(results.get(position));

        RequestOptions options = new RequestOptions().centerCrop().placeholder(R.drawable.placeholder).error(R.drawable.placeholder);
        Glide.with(context).load(results.get(position).getCover()).apply(options).into(holder.getResultCoverIV());
        try {
            holder.getSearchLayoutLL().setOnClickListener(view -> searchResultOnClickListener.onClick(results.get(position)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        holder.getResultTitleTV().setSelected(true);
    }

    @Override
    public int getItemCount() {
        return results.size();
    }
}
