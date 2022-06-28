package com.example.martialarts.view;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.martialarts.R;
import com.google.android.material.card.MaterialCardView;

public class MartialArtHolder extends RecyclerView.ViewHolder {
    private final TextView nameTV;
    private final TextView colorTV;
    private final TextView priceTV;
    private final MaterialCardView materialCard;

    public MartialArtHolder(@NonNull View itemView) {
        super(itemView);

        nameTV = itemView.findViewById(R.id.tv_name);
        colorTV = itemView.findViewById(R.id.tv_color);
        priceTV = itemView.findViewById(R.id.tv_price);
        materialCard = itemView.findViewById(R.id.martial_card);
    }

    public TextView getNameTV() {
        return nameTV;
    }

    public TextView getColorTV() {
        return colorTV;
    }

    public TextView getPriceTV() {
        return priceTV;
    }

    public MaterialCardView getMaterialCard() { return materialCard; }
}
