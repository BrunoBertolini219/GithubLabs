package com.brunoccbertolini.github.data.repository

import com.brunoccbertolini.base.injection.RuntimeScope
import com.brunoccbertolini.github.data.remote.datasource.GithubDataSource
import com.brunoccbertolini.github.data.remote.mapper.mapToRepoListModel
import com.brunoccbertolini.github.data.remote.mapper.mapToRepositoryListPrModel
import com.brunoccbertolini.github.data.remote.mapper.toGetRepoListQuery
import com.brunoccbertolini.github.domain.entity.GithubRepoItemModel
import com.brunoccbertolini.github.domain.entity.GithubRepoListDetail
import com.brunoccbertolini.github.domain.entity.GithubRepoListModel
import com.brunoccbertolini.github.domain.entity.GithubRepoOwnerModel
import com.brunoccbertolini.github.domain.entity.RepositoryPrItemModel
import com.brunoccbertolini.github.domain.repository.GithubRepository
import io.reactivex.Single
import javax.inject.Inject

@RuntimeScope
class GithubRepositoryImpl @Inject constructor(
    private val githubDataSource: GithubDataSource
) : GithubRepository {
    override fun getJavaRepoList(request: GithubRepoListDetail): Single<GithubRepoListModel> {
        return githubDataSource.getJavaRepoList(
            request.toGetRepoListQuery()
        ).map { it.mapToRepoListModel() }
    }

    override fun getRepositoryPulls(fullName: String): Single<List<RepositoryPrItemModel>> {
        return githubDataSource.getRepositoryPulls(fullName).map { it.mapToRepositoryListPrModel() }
    }
}

class GithubRepositoryFake @Inject constructor() : GithubRepository {
    override fun getJavaRepoList(request: GithubRepoListDetail): Single<GithubRepoListModel> {
        return Single.just(
            GithubRepoListModel(
                totalItems = 10000,
                totalPages = 333,
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
            )
        )
    }

    override fun getRepositoryPulls(fullName: String): Single<List<RepositoryPrItemModel>> {
        return Single.just(
            listOf(
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
    }
}