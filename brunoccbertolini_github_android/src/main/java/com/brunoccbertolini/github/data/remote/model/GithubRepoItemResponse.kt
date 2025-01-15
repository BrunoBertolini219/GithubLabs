package com.brunoccbertolini.github.data.remote.model

import com.google.gson.annotations.SerializedName

data class GithubRepoItemResponse(
    @SerializedName("name") val repositoryName: String,
    @SerializedName("full_name") val repositoryFullName: String,
    @SerializedName("description") val repositoryDescription: String?,
    @SerializedName("stargazers_count") val stars: Int,
    val owner: GithubRepoOwnerResponse,
    val forks: Int
)
