package com.sidharth.vidyakhoj.data

import retrofit2.http.GET

interface UniversityApi {

    @GET("search")
    suspend fun getUniversities(): List<University>
}