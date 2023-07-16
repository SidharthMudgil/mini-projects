package com.example.memepur.controller;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.memepur.R;
import com.example.memepur.model.Category;
import com.example.memepur.view.CategoryViewHolder;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder> {
    public interface CategoryClickListener {
        void categoryIsClicked(Category category);
    }

    private final ArrayList<Category> categoryList;
    private final CategoryClickListener categoryClickListener;

    public CategoryAdapter(ArrayList<Category> categoryList, CategoryClickListener categoryClickListener) {
        this.categoryList = categoryList;
        this.categoryClickListener = categoryClickListener;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_view_holder, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        String categoryNumber = String.valueOf(position + 1);
        holder.getCategoryNumberTV().setText(categoryNumber);
        holder.getCategoryNameTV().setText(categoryList.get(position).getCategoryName());

        holder.getView().setOnClickListener(v -> categoryClickListener.categoryIsClicked(categoryList.get(position)));
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }
}
