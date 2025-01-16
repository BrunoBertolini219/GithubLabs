package com.brunoccbertolini.github.data.remote.model

import com.google.gson.annotations.SerializedName

data class GithubRepoListResponse (
    @SerializedName("total_count") val totalItems: Int,
    val items: List<GithubRepoItemResponse>
)