package com.sidharth.navachar.domain.model

data class Post(
    val id: String,
    val author: Author,
    val type: PostType,
    val caption: String,
    val images: List<String>,
    val likes: Int,
    val totalComments: Int,
    val comments: List<Comment>,
)

enum class PostType {
    QUESTION, MARKETING
}