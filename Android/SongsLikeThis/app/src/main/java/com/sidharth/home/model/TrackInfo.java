package com.sidharth.home.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class TrackInfo implements Serializable {
    private final String name;
    private final String artist;
    private final String cover;
    private final int totalListeners;
    private final int playCount;
    private final int durationCount;

    public TrackInfo(String name, String artist, String cover, int totalListeners, int playCount, int durationCount) {
        this.name = name;
        this.artist = artist;
        this.cover = cover;
        this.totalListeners = totalListeners;
        this.playCount = playCount;
        this.durationCount = durationCount;
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public String getCover() {
        return cover;
    }

    public int getTotalListeners() {
        return totalListeners;
    }

    public int getPlayCount() {
        return playCount;
    }

    public int getDurationCount() {
        return durationCount;
    }

    @NonNull
    @Override
    public String toString() {
        return "TrackInfo{" +
                "name='" + name + '\'' +
                ", artist='" + artist + '\'' +
                ", cover='" + cover + '\'' +
                ", totalListeners=" + totalListeners +
                ", playCount=" + playCount +
                ", durationCount=" + durationCount +
                '}';
    }
}
