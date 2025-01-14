package com.brunoccbertolini.github.presentation.list

import com.brunoccbertolini.github.domain.entity.GithubRepoItemModel

sealed class JavaRepoListIntent {
    data object ActionLoadRepoList : JavaRepoListIntent()
    data class ActionLoadMoreRepoItems(val page: Int) : JavaRepoListIntent()
    data class ActionItemClick(val repoItem: GithubRepoItemModel) : JavaRepoListIntent()
}