package com.brunoccbertolini.github.di

import com.brunoccbertolini.github.data.remote.datasource.GithubDataSource
import com.brunoccbertolini.github.data.remote.datasource.GithubDataSourceRemote
import com.brunoccbertolini.github.data.repository.GithubRepositoryImpl
import com.brunoccbertolini.github.domain.repository.GithubRepository
import com.brunoccbertolini.github.domain.usecase.GetRepoListUseCase
import com.brunoccbertolini.github.domain.usecase.GetRepoListUseCaseImpl
import com.brunoccbertolini.github.domain.usecase.GetRepositoryPrListUseCase
import com.brunoccbertolini.github.domain.usecase.GetRepositoryPrListUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface GithubModule {
    @Binds
    fun providesGithubRepository(repository: GithubRepositoryImpl): GithubRepository
    @Binds
    fun providesGithubDataSource(dataSourceRemote: GithubDataSourceRemote): GithubDataSource
    @Binds
    fun provideGetRepoListUseCase(getRepoListUseCaseImpl: GetRepoListUseCaseImpl): GetRepoListUseCase
    @Binds
    fun provideGetRepoPrUseCase(getRepositoryPrListUseCaseImpl: GetRepositoryPrListUseCaseImpl): GetRepositoryPrListUseCase
}