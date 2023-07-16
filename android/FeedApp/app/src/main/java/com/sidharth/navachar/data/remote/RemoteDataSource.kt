package com.sidharth.navachar.data.remote

import com.sidharth.navachar.domain.model.Post

class RemoteDataSource {
    fun getPosts(): List<Post> {
        return DummyData.posts
    }
}