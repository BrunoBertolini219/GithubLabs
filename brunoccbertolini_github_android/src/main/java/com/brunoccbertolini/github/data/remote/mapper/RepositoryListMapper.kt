package com.brunoccbertolini.github.data.remote.mapper

import com.brunoccbertolini.github.data.remote.model.GithubRepoItemResponse
import com.brunoccbertolini.github.data.remote.model.GithubRepoListRequest
import com.brunoccbertolini.github.data.remote.model.GithubRepoListResponse
import com.brunoccbertolini.github.data.remote.model.GithubRepoOwnerResponse
import com.brunoccbertolini.github.domain.entity.GithubRepoItemModel
import com.brunoccbertolini.github.domain.entity.GithubRepoListDetail
import com.brunoccbertolini.github.domain.entity.GithubRepoListModel
import com.brunoccbertolini.github.domain.entity.GithubRepoOwnerModel

fun GithubRepoListDetail.toGetRepoListQuery(): Map<String, String> {
    val params = mutableMapOf<String, String>()
    params["q"] = "language:$language"
    params["page"] = "$page"
    params["sort"] = sort
    return params
}

fun GithubRepoListResponse.mapToRepoListModel() =
    GithubRepoListModel(
        hasNextPage = hasNextPage,
        items = items.map { it.mapToRepoItemModel() }
    )

fun GithubRepoItemResponse.mapToRepoItemModel() =
    GithubRepoItemModel(
        repositoryName = repositoryName,
        repositoryFullName = repositoryFullName,
        repositoryDescription = repositoryDescription ?: "",
        stars = stars,
        owner = owner.mapToRepoOwnerModel(),
        forks = forks
    )

fun GithubRepoOwnerResponse.mapToRepoOwnerModel() =
    GithubRepoOwnerModel(
        username = username,
        userAvatar = userAvatar
    )