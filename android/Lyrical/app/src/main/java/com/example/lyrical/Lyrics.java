package com.example.lyrical;

import com.google.gson.annotations.SerializedName;

public class Lyrics {

    @SerializedName("lyrics")
    private String lyrics;

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }
}
