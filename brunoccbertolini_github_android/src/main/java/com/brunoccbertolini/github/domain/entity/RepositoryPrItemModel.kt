package com.brunoccbertolini.github.domain.entity

data class RepositoryPrItemModel(
    val pullRequestName: String,
    val pullRequestDate: String,
    val pullRequestDescription: String,
    val pullRequestUrl: String,
    val user: GithubRepoOwnerModel
)