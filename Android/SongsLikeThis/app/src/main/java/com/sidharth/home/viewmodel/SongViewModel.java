package com.sidharth.home.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.sidharth.home.model.SongModel;

import java.util.List;

public class SongViewModel extends ViewModel {
    private LiveData<List<SongModel>> recommendedSongs;
    private LiveData<List<SongModel>> popularSongs;


//    private
}
