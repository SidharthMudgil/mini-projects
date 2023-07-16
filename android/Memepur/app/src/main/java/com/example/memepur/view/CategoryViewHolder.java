package com.example.memepur.view;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.memepur.R;

public class CategoryViewHolder extends RecyclerView.ViewHolder {
    private final TextView categoryNumberTV;
    private final TextView categoryNameTV;
    private final View view;

    public CategoryViewHolder(@NonNull View itemView) {
        super(itemView);

        categoryNumberTV = itemView.findViewById(R.id.category_number);
        categoryNameTV = itemView.findViewById(R.id.category_name);
        view = itemView.findViewById(R.id.category_view);
    }

    public TextView getCategoryNameTV() {
        return categoryNameTV;
    }

    public TextView getCategoryNumberTV() {
        return categoryNumberTV;
    }

    public View getView() { return view; }
}
