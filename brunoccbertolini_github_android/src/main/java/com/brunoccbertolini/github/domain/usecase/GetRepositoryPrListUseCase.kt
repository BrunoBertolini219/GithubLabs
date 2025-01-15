package com.brunoccbertolini.github.domain.usecase

import com.brunoccbertolini.github.domain.entity.RepositoryPrItemModel
import com.brunoccbertolini.github.domain.repository.GithubRepository
import io.reactivex.Single
import javax.inject.Inject

interface GetRepositoryPrListUseCase {
    operator fun invoke(fullName: String): Single<List<RepositoryPrItemModel>>
}

class GetRepositoryPrListUseCaseImpl @Inject constructor(
    private val githubRepository: GithubRepository
) : GetRepositoryPrListUseCase {
    override fun invoke(fullName: String): Single<List<RepositoryPrItemModel>> {
        return githubRepository.getRepositoryPulls(fullName)
    }
}