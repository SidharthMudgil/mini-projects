package com.example.lyrical;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private final String TAG = MainActivity.class.getSimpleName();

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextInputEditText artistName = findViewById(R.id.artist);
        TextInputEditText songTitle = findViewById(R.id.songTitle);

        Button searchButton = findViewById(R.id.search);
        searchButton.setEnabled(false);

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int artist = String.valueOf(artistName.getText()).trim().length();
                int title = String.valueOf(songTitle.getText()).trim().length();

                searchButton.setEnabled(artist != 0 && title != 0);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };

        artistName.addTextChangedListener(textWatcher);
        songTitle.addTextChangedListener(textWatcher);

        searchButton.setOnClickListener(v -> {
            String artist = String.valueOf(artistName.getText());
            String title = String.valueOf(songTitle.getText());

            ConstraintLayout mainLayout = findViewById(R.id.main_layout);
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(mainLayout.getWindowToken(), 0);

            requestLyrics(artist, title);
        });
    }

    private void requestLyrics(String artist, String title) {
        LyricsInterface lyricsInterface = RetrofitServiceGenerator.createRetrofitService(LyricsInterface.class);
        Call<Lyrics> call = lyricsInterface.getLyrics(artist, title);

        call.enqueue(new Callback<Lyrics>() {
            @Override
            public void onResponse(@NonNull Call<Lyrics> call, @NonNull Response<Lyrics> response) {
                TextView lyrics = findViewById(R.id.lyrics);
                if (response.isSuccessful()) {
                    lyrics.setText(response.body() != null ? response.body().getLyrics() : "null");
                    lyrics.setMovementMethod(new ScrollingMovementMethod());
                } else {
                    lyrics.setText(R.string.no_lyrics_found);
                    Log.e(TAG, response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Lyrics> call, @NonNull Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }
}