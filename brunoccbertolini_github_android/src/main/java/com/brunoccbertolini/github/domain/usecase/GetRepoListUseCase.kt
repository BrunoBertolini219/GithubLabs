package com.brunoccbertolini.github.domain.usecase

import com.brunoccbertolini.github.domain.entity.GithubRepoListDetail
import com.brunoccbertolini.github.domain.entity.GithubRepoListModel
import com.brunoccbertolini.github.domain.repository.GithubRepository
import io.reactivex.Single
import javax.inject.Inject

interface GetRepoListUseCase {
    operator fun invoke(detail: GithubRepoListDetail): Single<GithubRepoListModel>
}

class GetRepoListUseCaseImpl @Inject constructor(
    private val githubRepository: GithubRepository
) : GetRepoListUseCase {
    override fun invoke(detail: GithubRepoListDetail): Single<GithubRepoListModel> {
        return githubRepository.getJavaRepoList(detail)
    }
}