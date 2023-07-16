package com.sidharth.navachar.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sidharth.navachar.domain.model.Post
import com.sidharth.navachar.domain.usecase.GetPostsUseCase
import javax.inject.Inject

class FeedPagingSource @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase
) : PagingSource<Int, Post>() {
    companion object {
        private var nextPage = 1
    }

    override fun getRefreshKey(state: PagingState<Int, Post>): Int? {
        return state.anchorPosition?.let { pos ->
            val page = state.closestPageToPosition(pos)
            page?.prevKey?.minus(1) ?: page?.nextKey?.plus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Post> {
        return try {
            val posts = getPostsUseCase.execute()
            LoadResult.Page(
                posts,
                prevKey = null,
                nextKey = nextPage + 1
            ).also {
                nextPage += 1
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}