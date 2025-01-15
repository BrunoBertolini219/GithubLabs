package com.brunoccbertolini.github.data.remote.model

data class GithubRepoListRequest (
    val page: Int,
    val language: String,
    val sort: String
)