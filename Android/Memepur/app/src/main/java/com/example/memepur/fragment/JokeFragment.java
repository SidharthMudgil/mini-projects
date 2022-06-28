package com.example.memepur.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.memepur.R;
import com.example.memepur.controller.JokeAdapter;
import com.example.memepur.model.Category;
import com.example.memepur.model.Joke;

public class JokeFragment extends Fragment {
    private Category category;
    private static final String CATEGORY_ARGS = "args@joke";
    private RecyclerView recyclerView;


    public static JokeFragment newInstance(Category category) {
        JokeFragment jokeFragment = new JokeFragment();

        Bundle args = new Bundle();
        args.putSerializable(CATEGORY_ARGS, category);
        jokeFragment.setArguments(args);

        return jokeFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        assert getArguments() != null;
        category = (Category) getArguments().getSerializable(CATEGORY_ARGS);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_joke, container, false);

        recyclerView = view.findViewById(R.id.fragment_joke_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new JokeAdapter(category));
        recyclerView.setOnTouchListener((v, event) -> {
            view.findViewById(R.id.childScroll).getParent().requestDisallowInterceptTouchEvent(false);
            return false;
        });

        return view;
    }

    public void addJokeToCategory(Joke joke) {
        category.getJokeList().add(joke);
        JokeAdapter jokeAdapter = (JokeAdapter) recyclerView.getAdapter();
        assert jokeAdapter != null;
        jokeAdapter.notifyItemInserted(category.getJokeList().size() - 1);
    }
}