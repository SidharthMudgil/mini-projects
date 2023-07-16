package com.sidharth.navachar.domain.repository

import com.sidharth.navachar.domain.model.Post

interface FeedRepository {
    suspend fun getPosts(): List<Post>
}