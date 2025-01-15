package com.brunoccbertolini.github.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.brunoccbertolini.github.data.GithubMock
import com.brunoccbertolini.github.domain.repository.GithubRepository
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetRepoListUseCaseTest {
    private val mockModel = GithubMock()
    private lateinit var getRepositoryList: GetRepoListUseCaseImpl

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: GithubRepository

    @Before
    fun setUp() {
        getRepositoryList = GetRepoListUseCaseImpl(repository)
    }

    @Test
    fun `GIVEN repository list detail THEN should return repository list model`(){
        val repositoryListDetail = mockModel.githubRepoListDetail
        val repositoryListModel = mockModel.githubRepoList

        //Act
        getRepositoryList.invoke(repositoryListDetail)

        //Assert
        whenever(repository.getJavaRepoList(repositoryListDetail))
            .thenReturn(Single.just(repositoryListModel))
    }
}