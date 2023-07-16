package com.sidharth.navachar.ui.screens.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.sidharth.navachar.domain.model.Post

class SharedViewModel: ViewModel() {
    var post by mutableStateOf<Post?>(null)
        private set

    fun addPost(_post: Post) {
        post = _post
    }
}