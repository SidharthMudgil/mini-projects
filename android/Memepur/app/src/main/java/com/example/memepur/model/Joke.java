package com.example.memepur.model;

import java.io.Serializable;

public class Joke implements Serializable {
    private final String jokeBody;
    private final double jokeRating;

    public Joke(String jokeBody, double jokeRating) {
        this.jokeBody = jokeBody;
        this.jokeRating = jokeRating;
    }

    public String getJokeBody() {
        return jokeBody;
    }

    public double getJokeRating() {
        return jokeRating;
    }
}
