package com.sidharth.home.view;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.sidharth.songslikethis.R;
import com.sidharth.home.model.TrackInfo;

public class SimilarSongsViewHolder extends RecyclerView.ViewHolder {
    private final ImageView songCoverTV;
    private final TextView title;
    private final CardView cardView;

    public SimilarSongsViewHolder(@NonNull View itemView) {
        super(itemView);

        songCoverTV = itemView.findViewById(R.id.iv_cover_song);
        title = itemView.findViewById(R.id.tv_title);
        cardView = itemView.findViewById(R.id.item_card);
    }

    public void bind(Context context, TrackInfo model) {
        title.setText(model.getName());
        RequestOptions options = new RequestOptions().centerCrop().placeholder(R.drawable.placeholder).error(R.drawable.placeholder);
        Glide.with(context).load(model.getCover()).apply(options).into(songCoverTV);
    }

    public CardView getCardView() {
        return cardView;
    }
}
