package com.sidharth.navachar.data.repository

import com.sidharth.navachar.data.remote.RemoteDataSource
import com.sidharth.navachar.domain.model.Post
import com.sidharth.navachar.domain.repository.FeedRepository
import javax.inject.Inject

class FeedRepositoryImpl @Inject constructor(
    private val dataSource: RemoteDataSource
): FeedRepository {

    override suspend fun getPosts(): List<Post> {
        return dataSource.getPosts()
    }
}