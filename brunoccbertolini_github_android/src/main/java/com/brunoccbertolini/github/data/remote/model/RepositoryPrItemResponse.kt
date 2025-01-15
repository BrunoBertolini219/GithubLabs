package com.brunoccbertolini.github.data.remote.model

import com.google.gson.annotations.SerializedName

data class RepositoryPrItemResponse(
    @SerializedName("title") val pullRequestName: String,
    @SerializedName("created_at") val pullRequestDate: String,
    @SerializedName("body") val pullRequestDescription: String?,
    @SerializedName("html_url") val pullRequestUrl: String,
    val user: GithubRepoOwnerResponse
)