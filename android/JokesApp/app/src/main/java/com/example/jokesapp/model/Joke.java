package com.example.jokesapp.model;

public class Joke {
    private final String joke;
    private boolean isLiked;

    public Joke(String joke, boolean isLiked) {
        this.joke = joke;
        this.isLiked = isLiked;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }

    public String getJoke() {
        return joke;
    }
}
