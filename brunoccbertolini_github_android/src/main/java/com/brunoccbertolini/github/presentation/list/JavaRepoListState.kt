package com.brunoccbertolini.github.presentation.list

import com.brunoccbertolini.github.domain.entity.GithubRepoItemModel

sealed class JavaRepoListState {
    data class Loading(val isLoading: Boolean) : JavaRepoListState()
    data class Error(val errorMessage: String?) : JavaRepoListState()
    data class RepositoryListLoaded(val repoList: List<GithubRepoItemModel>) : JavaRepoListState()
    data class RepositoryItemClicked(val repoList: GithubRepoItemModel) : JavaRepoListState()
}