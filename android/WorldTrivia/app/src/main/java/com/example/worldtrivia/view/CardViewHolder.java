package com.example.worldtrivia.view;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.worldtrivia.R;

public class CardViewHolder extends RecyclerView.ViewHolder{
    private TextView questionTV;
    private ImageButton trueButton;
    private ImageButton falseButton;

    public CardViewHolder(@NonNull View itemView) {
        super(itemView);

        questionTV = itemView.findViewById(R.id.questionTV);
        trueButton = itemView.findViewById(R.id.trueButton);
        falseButton = itemView.findViewById(R.id.falseButton);
    }

    public TextView getQuestionTV() {
        return questionTV;
    }
    public ImageButton getTrueButton() {
        return trueButton;
    }
    public ImageButton getFalseButton() {
        return falseButton;
    }
}
