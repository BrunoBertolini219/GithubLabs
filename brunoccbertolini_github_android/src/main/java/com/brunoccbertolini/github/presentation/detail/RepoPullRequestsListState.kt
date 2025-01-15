package com.brunoccbertolini.github.presentation.detail

import com.brunoccbertolini.github.domain.entity.RepositoryPrItemModel

sealed class RepoPullRequestsListState {
    data class Loading(val isLoading: Boolean) : RepoPullRequestsListState()
    data class Error(val errorMessage: String?) : RepoPullRequestsListState()
    data class PullRequestListLoaded(val repoList: List<RepositoryPrItemModel>) : RepoPullRequestsListState()
    data class PullRequestItemClicked(val repoList: RepositoryPrItemModel) : RepoPullRequestsListState()
}