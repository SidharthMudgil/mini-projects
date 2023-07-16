package com.sidharth.navachar.ui.screens.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sidharth.navachar.domain.usecase.GetPostsUseCase
import java.lang.IllegalArgumentException
import javax.inject.Inject

class FeedViewModelFactory @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase
): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FeedViewModel::class.java)) {
            return FeedViewModel(getPostsUseCase) as T
        }
        throw IllegalArgumentException("ViewModel class not found")
    }
}