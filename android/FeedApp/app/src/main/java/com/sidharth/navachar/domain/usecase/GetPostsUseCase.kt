package com.sidharth.navachar.domain.usecase

import com.sidharth.navachar.domain.model.Post

interface GetPostsUseCase {
    suspend fun execute(): List<Post>
}