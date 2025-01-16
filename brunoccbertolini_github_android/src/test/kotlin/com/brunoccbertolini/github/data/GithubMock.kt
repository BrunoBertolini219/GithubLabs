package com.brunoccbertolini.github.data

import com.brunoccbertolini.github.data.remote.model.GithubRepoItemResponse
import com.brunoccbertolini.github.data.remote.model.GithubRepoListResponse
import com.brunoccbertolini.github.data.remote.model.GithubRepoOwnerResponse
import com.brunoccbertolini.github.data.remote.model.RepositoryPrItemResponse
import com.brunoccbertolini.github.domain.entity.GithubRepoItemModel
import com.brunoccbertolini.github.domain.entity.GithubRepoListDetail
import com.brunoccbertolini.github.domain.entity.GithubRepoListModel
import com.brunoccbertolini.github.domain.entity.GithubRepoOwnerModel
import com.brunoccbertolini.github.domain.entity.RepositoryPrItemModel

data class GithubMock(
    val exception: RuntimeException = RuntimeException("Error"),
    val page: Int = 1,
    val sort: String = "stars",
    val language: String = "Java",
    val githubRepoListModel: GithubRepoListModel = GithubRepoListModel(
        totalItems = 1000,
        items = listOf(
            GithubRepoItemModel(
                repositoryName = "hello-algo",
                repositoryDescription = "repository description",
                repositoryFullName = "krahets/hello-algo",
                owner = GithubRepoOwnerModel(
                    username = "krahets",
                    userAvatar = "https://avatars.githubusercontent.com/u/26993056?v=4"
                ),
                forks = 13373,
                stars = 106966
            )
        )
    ),
    val githubRepoListResponse: GithubRepoListResponse = GithubRepoListResponse(
        totalItems = 1000,
        items = listOf(
            GithubRepoItemResponse(
                repositoryName = "hello-algo",
                repositoryDescription = "repository description",
                repositoryFullName = "krahets/hello-algo",
                owner = GithubRepoOwnerResponse(
                    username = "krahets",
                    userAvatar = "https://avatars.githubusercontent.com/u/26993056?v=4"
                ),
                forks = 13373,
                stars = 106966
            )
        )
    ),
    val githubRepoListDetail: GithubRepoListDetail = GithubRepoListDetail(
        page = 1,
        language = "Java",
        sort = "stars"
    ),
    val githubPrListModel: List<RepositoryPrItemModel> = listOf(
        RepositoryPrItemModel(
            pullRequestDate = "2025-01-11T13:08:10Z",
            pullRequestDescription = "\"This is replaceRange but, It does insert.\\r\\nWhen set the same Index on start and end, it have done append CharSequence currently.",
            pullRequestUrl = "https://github.com/Jaehwa-Noh",
            pullRequestName = "Jaehwa-Noh",
            user = GithubRepoOwnerModel(
                username = "krahets",
                userAvatar = "https://avatars.githubusercontent.com/u/26993056?v=4"
            )
        )
    ),
    val githubPrListResponse: List<RepositoryPrItemResponse> = listOf(
        RepositoryPrItemResponse(
            pullRequestDate = "2025-01-11T13:08:10Z",
            pullRequestDescription = "\"This is replaceRange but, It does insert.\\r\\nWhen set the same Index on start and end, it have done append CharSequence currently.",
            pullRequestUrl = "https://github.com/Jaehwa-Noh",
            pullRequestName = "Jaehwa-Noh",
            user = GithubRepoOwnerResponse(
                username = "krahets",
                userAvatar = "https://avatars.githubusercontent.com/u/26993056?v=4"
            )
        )
    )
)