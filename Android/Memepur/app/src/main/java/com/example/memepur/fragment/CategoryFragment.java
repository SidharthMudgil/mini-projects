package com.example.memepur.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.memepur.R;
import com.example.memepur.controller.CategoryAdapter;
import com.example.memepur.model.Category;
import com.example.memepur.model.CategoryManager;

import java.util.ArrayList;

public class CategoryFragment extends Fragment implements CategoryAdapter.CategoryClickListener, CategoryManager.CategoryUpdateListener {

    public interface CategoryInteractionListener {
        void categoryIsClicked(Category category);
    }

    private CategoryInteractionListener categoryInteractionListener;
    private CategoryManager categoryManager;

    public static CategoryFragment newInstance() {
        return new CategoryFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof CategoryInteractionListener) {
            categoryInteractionListener = (CategoryInteractionListener) context;
        } else {
            throw new RuntimeException("Category Fragment not a instance of MainActivity");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.fragment_category_rv);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        categoryManager = new CategoryManager(getActivity(), recyclerView, this);
        ArrayList<Category> categoryList = categoryManager.retrieveCategoryList();
        recyclerView.setAdapter(new CategoryAdapter(categoryList, this));

        return view;
    }

    public void createNewCategory(Category category) {
        categoryManager.addNewCategory(category);
    }

    @Override
    public void categoryIsClicked(Category category) {
        categoryInteractionListener.categoryIsClicked(category);
    }

    @Override
    public void updateRecyclerView(RecyclerView recyclerView, ArrayList<Category> categoryArrayList) {
        CategoryAdapter adapter = new CategoryAdapter(categoryArrayList, this);
        recyclerView.setAdapter(adapter);
    }
}