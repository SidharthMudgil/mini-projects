package com.example.lyrical;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface LyricsInterface {
    @GET("{artist}/{title}")
    Call<Lyrics> getLyrics(@Path(value = "artist") String artist, @Path(value = "title") String title);
}
