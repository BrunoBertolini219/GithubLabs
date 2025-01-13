package com.brunoccbertolini.github.data.remote.model

import com.google.gson.annotations.SerializedName

data class GithubRepoListResponse (
    @SerializedName("incomplete_results") val hasNextPage: Boolean,
    val items: List<GithubRepoItemResponse>
)