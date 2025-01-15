package com.brunoccbertolini.github.data

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
    val githubRepoList: GithubRepoListModel = GithubRepoListModel(
        hasNextPage = false,
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
    val githubRepoListDetail: GithubRepoListDetail = GithubRepoListDetail(
        page = 1,
        language = "Java",
        sort = "stars"
    ),
    val githubPrList: List<RepositoryPrItemModel> = listOf(
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
    )
)