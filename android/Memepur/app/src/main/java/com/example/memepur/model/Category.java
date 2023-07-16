package com.example.memepur.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Category implements Serializable {
    private final String categoryName;
    private ArrayList<Joke> JokeList;

    public Category(String categoryName, ArrayList<Joke> jokeList) {
        this.categoryName = categoryName;
        JokeList = jokeList;
    }

    public ArrayList<Joke> getJokeList() {
        return JokeList;
    }

    public void setJokeList(ArrayList<Joke> jokeList) {
        JokeList = jokeList;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
