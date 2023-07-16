package com.sidharth.search;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.sidharth.songslikethis.R;
import com.sidharth.home.HomeFragment;
import com.sidharth.home.model.Track;
import com.sidharth.home.model.TrackInfo;
import com.sidharth.search.controller.SearchItemAdapter;
import com.sidharth.utils.URLs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity implements SearchItemAdapter.SearchResultOnClickListener {
    private EditText searchView;
    private View progressBar;
    private SearchItemAdapter adapter;
    private ArrayList<TrackInfo> trackInfoArrayList;
    RequestQueue requestQueue;
    NetworkRequest networkRequest;
    Dialog dialog;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        networkRequest = new NetworkRequest.Builder()
                .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
                .build();

        ConnectivityManager connectivityManager = getSystemService(ConnectivityManager.class);
        connectivityManager.requestNetwork(networkRequest, networkCallback);

        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.no_internet_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        requestQueue = Volley.newRequestQueue(this);

        progressBar = findViewById(R.id.progress_bar);
        RecyclerView recyclerView = findViewById(R.id.rv_search_result);
        searchView = findViewById(R.id.et_search_view);
        trackInfoArrayList = new ArrayList<>();
        adapter = new SearchItemAdapter(this, trackInfoArrayList, this);

        searchView.requestFocus();
        searchView.setOnEditorActionListener((textView, i, keyEvent) -> {
            if (i == EditorInfo.IME_ACTION_SEARCH) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                if (searchView.getText().toString().length() < 2) {
                    trackInfoArrayList.clear();
                    adapter.notifyDataSetChanged();
                } else {
                    getSearchResult(searchView.getText().toString());
                }
                return true;
            }
            return false;
        });

//        searchView.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @SuppressLint("NotifyDataSetChanged")
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//            }
//
//            @SuppressLint("NotifyDataSetChanged")
//            @Override
//            public void afterTextChanged(Editable editable) {
//                if (editable.toString().length() < 3) {
//                    trackInfoArrayList.clear();
//                    adapter.notifyDataSetChanged();
//                } else {
//                    getSearchResult(editable.toString());
//                }
//            }
//        });

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @SuppressLint("NotifyDataSetChanged")
    private void getSearchResult(String title) {
        progressBar.setVisibility(View.VISIBLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(URLs.getURLForSearchSong(title), response -> {
            trackInfoArrayList.clear();
            adapter.notifyDataSetChanged();
            try {
                JSONArray tracks = response.getJSONObject("results").getJSONObject("trackmatches").getJSONArray("track");
                for (int i = 0; i < tracks.length(); i++) {
                    JSONObject obj = tracks.getJSONObject(i);
                    String name = obj.getString("name");
                    String artist = obj.getString("artist");

                    Track _track = new Track(name, artist);
                    Log.d("response_search", _track.toString());
                    getSongDetails(_track);
                }
                progressBar.setVisibility(View.GONE);
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
            Log.d("response_search", "error");
            progressBar.setVisibility(View.GONE);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        });

        requestQueue.add(jsonObjectRequest);
    }

    private void getSongDetails(Track track) {
        @SuppressLint("NotifyDataSetChanged") JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(URLs.getURLForTrackInfo(track.getName(), track.getArtist()), response -> {
            try {
                JSONObject obj = response.getJSONObject("track");
                int listeners = obj.getInt("listeners");
                int duration = obj.getInt("duration");
                int playcount = obj.getInt("playcount");
                String cover = obj.getJSONObject("album").getJSONArray("image").getJSONObject(3).getString("#text");

                TrackInfo trackInfo = new TrackInfo(track.getName(), track.getArtist(), cover, listeners, playcount, duration);
                Log.d("response_track_info", trackInfo.toString());
                trackInfoArrayList.add(trackInfo);
                adapter.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> Log.d("response_track_info", "error"));

        requestQueue.add(jsonObjectRequest);
    }

    private void performSearch(TrackInfo trackInfo) {
        try {
            searchView.clearFocus();
            trackInfoArrayList.clear();
            InputMethodManager in = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            in.hideSoftInputFromWindow(searchView.getWindowToken(), 0);
            Intent intent = getIntent();
            intent.putExtra(HomeFragment.SEARCH_RESULT_KEY, trackInfo);
            setResult(RESULT_OK, intent);
            finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final ConnectivityManager.NetworkCallback networkCallback = new ConnectivityManager.NetworkCallback() {
        @Override
        public void onAvailable(@NonNull Network network) {
            super.onAvailable(network);
            dismissDialog();
        }

        @Override
        public void onLost(@NonNull Network network) {
            super.onLost(network);
            showDialog();
        }

        @Override
        public void onCapabilitiesChanged(@NonNull Network network, @NonNull NetworkCapabilities networkCapabilities) {
            super.onCapabilitiesChanged(network, networkCapabilities);
        }
    };

    private void showDialog() {
        runOnUiThread(() -> dialog.show());
    }

    @SuppressLint("NotifyDataSetChanged")
    private void dismissDialog() {
        runOnUiThread(() -> {
            dialog.dismiss();
            if (searchView != null) {
                if (searchView.getText().toString().length() < 2) {
                    trackInfoArrayList.clear();
                    adapter.notifyDataSetChanged();
                } else {
                    getSearchResult(searchView.getText().toString());
                }
            }
        });
    }

    @Override
    public void onClick(TrackInfo trackInfo) {
        try {
            performSearch(trackInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}