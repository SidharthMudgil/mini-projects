package com.sidharth.navachar.ui.screens.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.sidharth.navachar.data.paging.FeedPagingSource
import com.sidharth.navachar.domain.usecase.GetPostsUseCase
import javax.inject.Inject

class FeedViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase
) : ViewModel() {
    val postsPager = Pager(
        PagingConfig(pageSize = 10)
    ) {
        FeedPagingSource(getPostsUseCase)
    }.flow.cachedIn(viewModelScope)
}