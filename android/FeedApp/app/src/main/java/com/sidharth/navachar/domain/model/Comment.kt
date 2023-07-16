package com.sidharth.navachar.domain.model

data class Comment(
    val id: String,
    val author: Author,
    val comment: String,
    val likes: Int,
)
