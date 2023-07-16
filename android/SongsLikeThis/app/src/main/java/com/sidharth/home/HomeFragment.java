package com.sidharth.home;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.sidharth.songslikethis.R;
import com.sidharth.home.controller.PopularSongsAdapter;
import com.sidharth.home.controller.SimilarSongsAdapter;
import com.sidharth.home.model.Track;
import com.sidharth.home.model.TrackInfo;
import com.sidharth.search.SearchActivity;
import com.sidharth.utils.URLs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements PopularSongsAdapter.PopularSongClickListener, SimilarSongsAdapter.SimilarSongClickListener {

    public static final String SEARCH_RESULT_KEY = "com.songlikethis.search@key";
    private RequestQueue requestQueue;

    private SimilarSongsAdapter similarSongsAdapter;
    private PopularSongsAdapter popularSongsAdapter;

    private ArrayList<TrackInfo> similarSongsModels;
    private ArrayList<TrackInfo> popularSongsModels;

    private View similarProgressBar;
    private View popularProgressBar;

    ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result != null) {
            Intent resultData = result.getData();
            if (resultData != null) {
                Bundle bundle = resultData.getExtras();

                TrackInfo trackInfo = (TrackInfo) bundle.getSerializable(SEARCH_RESULT_KEY);
                Log.d("selected_song", trackInfo.toString());
                getSimilarSongs(trackInfo);
            }
        }
    });

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        if (getActivity() != null) {
            MobileAds.initialize(getActivity(), initializationStatus -> {
            });
        }
        AdView mAdView = view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();

        requestQueue = Volley.newRequestQueue(getActivity());

        similarSongsModels = new ArrayList<>();
        popularSongsModels = new ArrayList<>();
        similarProgressBar = view.findViewById(R.id.progress_bar_similar_songs);
        popularProgressBar = view.findViewById(R.id.progress_bar_popular_songs);
        RecyclerView similarRV = view.findViewById(R.id.rv_similar_songs);
        RecyclerView popularRV = view.findViewById(R.id.rv_popular_songs);
        similarSongsAdapter = new SimilarSongsAdapter(getActivity(), similarSongsModels, this);
        popularSongsAdapter = new PopularSongsAdapter(getActivity(), popularSongsModels, this);

        mAdView.loadAd(adRequest);
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError adError) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdImpression() {
                // Code to be executed when an impression is recorded
                // for an ad.
            }

            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }
        });

        TextView searchView = view.findViewById(R.id.et_search_view);
        searchView.setOnClickListener(view1 -> {
            Intent intent = new Intent(getContext(), SearchActivity.class);
            launcher.launch(intent);
        });

        similarRV.setAdapter(similarSongsAdapter);
        popularRV.setAdapter(popularSongsAdapter);
        similarRV.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        popularRV.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(similarRV);
        new LinearSnapHelper().attachToRecyclerView(popularRV);

        loadPopularSongs();
        return view;
    }

    private void loadPopularSongs() {
        popularProgressBar.setVisibility(View.VISIBLE);
        similarProgressBar.setVisibility(View.VISIBLE);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(URLs.URL_TOP_TRACKS, response -> {
            try {
                JSONArray tracks = response.getJSONObject("tracks").getJSONArray("track");
                for (int i = 0; i < tracks.length(); i++) {
                    JSONObject obj = tracks.getJSONObject(i);
                    String name = obj.getString("name");
                    String artist = obj.getJSONObject("artist").getString("name");

                    Track track = new Track(name, artist);
                    getSongDetails(track, i, false);
                }
                popularProgressBar.setVisibility(View.GONE);
                similarProgressBar.setVisibility(View.GONE);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
            Log.d("response_top_tracks", "error");
            popularProgressBar.setVisibility(View.GONE);
            similarProgressBar.setVisibility(View.GONE);
        });

        requestQueue.add(jsonObjectRequest);
    }

    private void getSimilarSongs(TrackInfo trackInfo) {
        similarProgressBar.setVisibility(View.VISIBLE);

        @SuppressLint("NotifyDataSetChanged") JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(URLs.getURLForSimilarSongs(trackInfo.getName(), trackInfo.getArtist()), response -> {
            try {
                similarSongsModels.clear();
                similarSongsAdapter.notifyDataSetChanged();
                JSONArray tracks = response.getJSONObject("similartracks").getJSONArray("track");
                for (int i = 0; i < tracks.length(); i++) {
                    JSONObject obj = tracks.getJSONObject(i);
                    String name = obj.getString("name");
                    String artist = obj.getJSONObject("artist").getString("name");

                    Track track = new Track(name, artist);
                    getSongDetails(track, i, true);
                }
                similarProgressBar.setVisibility(View.GONE);
            } catch (JSONException e) {
                e.printStackTrace();
                similarProgressBar.setVisibility(View.GONE);
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        }, error -> Log.d("response_similar_tracks", "error"));

        requestQueue.add(jsonObjectRequest);
    }

    private void getSongDetails(Track track, int pos, boolean forSimilar) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(URLs.getURLForTrackInfo(track.getName(), track.getArtist()), response -> {
            try {
                JSONObject obj = response.getJSONObject("track");
                int listeners = obj.getInt("listeners");
                int duration = obj.getInt("duration");
                int playcount = obj.getInt("playcount");
                String cover = obj.getJSONObject("album").getJSONArray("image").getJSONObject(3).getString("#text");

                TrackInfo trackInfo = new TrackInfo(track.getName(), track.getArtist(), cover, listeners, playcount, duration);

                if (forSimilar) {
                    similarSongsModels.add(trackInfo);
                    similarSongsAdapter.notifyItemInserted(pos);
                } else {
                    similarSongsModels.add(trackInfo);
                    similarSongsAdapter.notifyItemInserted(pos);
                    popularSongsModels.add(trackInfo);
                    popularSongsAdapter.notifyItemInserted(pos);
                }
            } catch (JSONException | IndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        }, error -> Log.d("response_track_info", "error"));

        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public void onClick(TrackInfo trackInfo) {
        showDialog(trackInfo);
    }

    private void showDialog(TrackInfo trackInfo) {
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.song_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ImageView cover = dialog.findViewById(R.id.iv_cover_song);
        TextView title = dialog.findViewById(R.id.song_title);
        TextView subtitle = dialog.findViewById(R.id.song_subtitle);

        title.setText(trackInfo.getName());
        subtitle.setText(trackInfo.getArtist());

        RequestOptions options = new RequestOptions().centerCrop().placeholder(R.drawable.placeholder).error(R.drawable.placeholder);
        Glide.with(this).load(trackInfo.getCover()).apply(options).into(cover);

        dialog.show();
    }
}