package com.brunoccbertolini.github.domain.entity

data class GithubRepoItemModel(
    val repositoryName: String,
    val repositoryFullName: String,
    val repositoryDescription: String,
    val stars: Int,
    val owner: GithubRepoOwnerModel,
    val forks: Int,

    )
