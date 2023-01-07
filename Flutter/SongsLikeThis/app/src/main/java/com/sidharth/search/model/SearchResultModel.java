package com.sidharth.search.model;

import java.io.Serializable;

public class SearchResultModel implements Serializable {
    private final int key;
    private final String title;
    private final String subtitle;
    private final String coverURL;
    private final int author;

    public SearchResultModel(int key, String title, String subtitle, String coverURL, int author) {
        this.key = key;
        this.title = title;
        this.subtitle = subtitle;
        this.coverURL = coverURL;
        this.author = author;
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

    public String getCoverURL() {
        return coverURL;
    }

    public int getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "SearchResultModel{" +
                "key=" + key +
                ", title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", coverURL='" + coverURL + '\'' +
                ", author=" + author +
                '}';
    }
}
