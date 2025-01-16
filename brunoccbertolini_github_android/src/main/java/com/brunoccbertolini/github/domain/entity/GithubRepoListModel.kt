package com.brunoccbertolini.github.domain.entity

data class GithubRepoListModel(
    val totalItems: Int,
    val totalPages: Int,
    val items: List<GithubRepoItemModel>
)