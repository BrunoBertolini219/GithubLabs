package com.brunoccbertolini.github.domain.entity

data class GithubRepoListModel(
    val hasNextPage: Boolean,
    val items: List<GithubRepoItemModel>
)