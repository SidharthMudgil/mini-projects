package com.sidharth.home.model;

import androidx.annotation.NonNull;

public class Track {
    private final String name;
    private final String artist;

    public Track(String name, String artist) {
        this.name = name;
        this.artist = artist;
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    @NonNull
    @Override
    public String toString() {
        return "Track{" +
                "name='" + name + '\'' +
                ", artist='" + artist + '\'' +
                '}';
    }
}
