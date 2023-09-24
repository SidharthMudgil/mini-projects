package com.sidharth.vidyakhoj.data

import com.google.gson.annotations.SerializedName

data class University(
    @SerializedName("name") val name: String,
    @SerializedName("state-province") val state: String?,
    @SerializedName("country") val country: String,
    @SerializedName("alpha_two_code") val code: String,
    @SerializedName("domains") val domains: List<String>,
    @SerializedName("web_pages") val webpages: List<String>,
)
