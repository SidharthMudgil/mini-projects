package com.example.jokesapp.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jokesapp.R;
import com.example.jokesapp.controller.FavJokeAdapter;
import com.example.jokesapp.model.Joke;
import com.example.jokesapp.model.JokeManager;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class FavJokesFragment extends Fragment {
    RecyclerView recyclerView;
    FavJokeAdapter favJokeAdapter;
    JokeManager jokeManager;
    private List<Joke> jokeList = new ArrayList<>();

    private Joke deletedJoke;

    public FavJokesFragment() {
    }

    public static FavJokesFragment newInstance() {
        return new FavJokesFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        jokeManager = new JokeManager(context);
        jokeList.clear();
        if (jokeManager.retrieveJokes().size() > 0) {
            jokeList.addAll(jokeManager.retrieveJokes());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fav_jokes, container, false);

        if (view != null) {
            recyclerView = view.findViewById(R.id.rv);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            favJokeAdapter = new FavJokeAdapter(jokeList, getContext());
            recyclerView.setAdapter(favJokeAdapter);

            ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
            itemTouchHelper.attachToRecyclerView(recyclerView);
        }
        return view;
    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            final int position = viewHolder.getAdapterPosition();
            switch (direction) {
                case ItemTouchHelper.LEFT:
                case ItemTouchHelper.RIGHT:
                    deletedJoke = jokeList.get(position);
                    jokeManager.removeJoke(jokeList.get(position));
                    jokeList.remove(position);
                    favJokeAdapter.notifyItemRemoved(position);

                    Snackbar.make(recyclerView, " Joke is Removed", Snackbar.LENGTH_LONG)
                            .setAction("Undo", v -> {
                                jokeList.add(position, deletedJoke);
                                jokeManager.saveJoke(deletedJoke);
                                favJokeAdapter.notifyItemInserted(position);
                            }).show();
                    break;
                default:
                    break;
            }
        }
    };
}