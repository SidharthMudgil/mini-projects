package com.sidharth.home.view;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.sidharth.songslikethis.R;
import com.sidharth.home.model.TrackInfo;

public class PopularSongsViewModel extends RecyclerView.ViewHolder {
    private final ImageView songCoverIV;
    private final CardView cardView;

    public PopularSongsViewModel(@NonNull View itemView) {
        super(itemView);

        songCoverIV = itemView.findViewById(R.id.iv_cover_song);
        cardView = itemView.findViewById(R.id.item_card);
    }

    public void bind(Context context, TrackInfo model) {
        RequestOptions options = new RequestOptions().centerCrop().placeholder(R.drawable.placeholder).error(R.drawable.placeholder);
        Glide.with(context).load(model.getCover()).apply(options).into(songCoverIV);
    }

    public CardView getCardView() {
        return cardView;
    }
}
