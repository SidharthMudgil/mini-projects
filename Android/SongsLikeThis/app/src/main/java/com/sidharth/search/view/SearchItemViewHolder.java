package com.sidharth.search.view;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sidharth.songslikethis.R;
import com.sidharth.home.model.TrackInfo;

public class SearchItemViewHolder extends RecyclerView.ViewHolder {
    private final TextView resultTitleTV;
    private final TextView resultSubtitleTV;
    private final ImageView resultCoverIV;
    private final LinearLayout searchLayoutLL;

    public SearchItemViewHolder(@NonNull View itemView) {
        super(itemView);

        resultTitleTV = itemView.findViewById(R.id.tv_result_title);
        resultSubtitleTV = itemView.findViewById(R.id.tv_result_subtitle);
        resultCoverIV = itemView.findViewById(R.id.iv_result_icon);
        searchLayoutLL = itemView.findViewById(R.id.ll_search);
    }

    public void bind(TrackInfo model) {
        resultTitleTV.setText(model.getName());
        resultSubtitleTV.setText(model.getArtist());
    }

    public TextView getResultTitleTV() {
        return resultTitleTV;
    }

    public TextView getResultSubtitleTV() {
        return resultSubtitleTV;
    }

    public ImageView getResultCoverIV() {
        return resultCoverIV;
    }

    public LinearLayout getSearchLayoutLL() {
        return searchLayoutLL;
    }
}
