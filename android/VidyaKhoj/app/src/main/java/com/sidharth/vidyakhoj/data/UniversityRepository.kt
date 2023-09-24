package com.sidharth.vidyakhoj.data

import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UniversityRepository @Inject constructor(
    private val universityApi: UniversityApi
) {
    suspend fun getUniversities() = flow {
        emit(universityApi.getUniversities())
    }
}