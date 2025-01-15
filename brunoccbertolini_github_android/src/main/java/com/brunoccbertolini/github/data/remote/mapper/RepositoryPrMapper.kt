package com.brunoccbertolini.github.data.remote.mapper

import com.brunoccbertolini.github.data.remote.model.RepositoryPrItemResponse
import com.brunoccbertolini.github.domain.entity.RepositoryPrItemModel

fun List<RepositoryPrItemResponse>.mapToRepositoryListPrModel() =
    this.map { it.mapToRepositoryPrItemModel() }

fun RepositoryPrItemResponse.mapToRepositoryPrItemModel() =
    RepositoryPrItemModel(
        pullRequestName = pullRequestName,
        pullRequestUrl = pullRequestUrl,
        pullRequestDescription = pullRequestDescription ?: "",
        pullRequestDate = pullRequestDate,
        user = user.mapToRepoOwnerModel(),
    )