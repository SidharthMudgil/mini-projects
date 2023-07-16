package com.sidharth.navachar.domain.usecase

import com.sidharth.navachar.domain.model.Post
import com.sidharth.navachar.domain.repository.FeedRepository
import javax.inject.Inject

class GetPostsUseCaseImpl @Inject constructor(
    private val feedRepository: FeedRepository
): GetPostsUseCase {

    override suspend fun execute(): List<Post> {
        return feedRepository.getPosts()
    }
}