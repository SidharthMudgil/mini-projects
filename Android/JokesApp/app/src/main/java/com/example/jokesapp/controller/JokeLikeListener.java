package com.example.jokesapp.controller;

import com.example.jokesapp.model.Joke;

public interface JokeLikeListener {
    void jokeIsLiked(Joke joke);
}
