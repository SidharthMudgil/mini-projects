package com.sidharth.vidyakhoj.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sidharth.vidyakhoj.data.University
import com.sidharth.vidyakhoj.data.UniversityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UniversityViewModel @Inject constructor(
    private val repository: UniversityRepository
) : ViewModel() {
    private val _universities = MutableStateFlow<List<University>>(listOf())
    val universities: StateFlow<List<University>> get() = _universities

    fun fetchUniversities() = viewModelScope.launch {
        repository.getUniversities().collect {
            _universities.emit(it)
        }
    }
}