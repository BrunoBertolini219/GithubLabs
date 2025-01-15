package com.brunoccbertolini.github.presentation.detail

import com.brunoccbertolini.github.domain.entity.RepositoryPrItemModel

sealed class RepoPullRequestsListIntent {
    data class ActionLoadPrList(val fullName: String) : RepoPullRequestsListIntent()
    data class ActionLoadMoreRepoItems(val page: Int) : RepoPullRequestsListIntent()
    data class ActionItemClick(val repoItem: RepositoryPrItemModel) : RepoPullRequestsListIntent()
}