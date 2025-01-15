package com.brunoccbertolini.github.data.remote.model

import com.google.gson.annotations.SerializedName

data class GithubRepoOwnerResponse(
    @SerializedName("login") val username: String,
    @SerializedName("avatar_url") val userAvatar: String
)
