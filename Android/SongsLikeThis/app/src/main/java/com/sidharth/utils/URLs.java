package com.sidharth.utils;

public class URLs {
    private static final String BASE_URL = "https://ws.audioscrobbler.com/2.0/";
    private static final String API_KEY = "46de8b9bb65e5ab6b35625b36ae68653"; //todo

    public static final String URL_TOP_TRACKS = BASE_URL + "?method=chart.gettoptracks&format=json&api_key=" + API_KEY;

    public static String getURLForSearchSong(String name) {
        return BASE_URL + "?method=track.search&format=json&api_key=" + API_KEY + "&limit=10&track=" + name;
    }

    public static String getURLForTrackInfo(String name, String artist) {
        return BASE_URL + "?method=track.getInfo&format=json&api_key=" + API_KEY + "&track=" + name + "&artist=" + artist;
    }

    public static String getURLForSimilarSongs(String name, String artist) {
        return BASE_URL + "?method=track.getsimilar&format=json&api_key=" + API_KEY + "&track=" + name + "&artist=" + artist;
    }
}
