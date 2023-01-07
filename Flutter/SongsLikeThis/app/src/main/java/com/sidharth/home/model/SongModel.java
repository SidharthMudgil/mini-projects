package com.sidharth.home.model;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;

import java.io.Serializable;

@Keep
public class SongModel implements Serializable {
    private final int key;
    private final String title;
    private final String subtitle;
    private final String cover;

    public SongModel(int key, String title, String subtitle, String cover) {
        this.key = key;
        this.title = title;
        this.subtitle = subtitle;
        this.cover = cover;
    }

    public SongModel(Builder builder) {
        title = builder.title;
        key = builder.key;
        subtitle = builder.subtitle;
        cover = builder.cover;
    }

    public String getTitle() {
        return title;
    }

    public int getKey() {
        return key;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getCover() {
        return cover;
    }

    @NonNull
    @Override
    public String toString() {
        return "SongModel{" +
                "key=" + key +
                ", title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", cover='" + cover + '\'' +
                '}';
    }

    public static class Builder {
        int key;
        String title;
        String subtitle;
        String cover;

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setKey(int key) {
            this.key = key;
            return this;
        }

        public Builder setSubtitle(String subtitle) {
            this.subtitle = subtitle;
            return this;
        }

        public Builder setCover(String cover) {
            this.cover = cover;
            return this;
        }

        public SongModel build() {
            return new SongModel(this);
        }
    }
}
