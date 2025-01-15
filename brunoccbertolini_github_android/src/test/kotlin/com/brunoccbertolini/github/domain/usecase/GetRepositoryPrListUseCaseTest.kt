package com.brunoccbertolini.github.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.brunoccbertolini.github.data.GithubMock
import com.brunoccbertolini.github.domain.repository.GithubRepository
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetRepositoryPrListUseCaseTest {
    private val mockModel = GithubMock()
    private lateinit var getRepositoryPrList: GetRepositoryPrListUseCaseImpl

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: GithubRepository

    @Before
    fun setUp() {
        getRepositoryPrList = GetRepositoryPrListUseCaseImpl(repository)
    }

    @Test
    fun `GIVEN full name THEN should return repository prs model`(){
        //Arrange
        val prList = mockModel.githubPrList

        //Act
        getRepositoryPrList.invoke("owner/repository")

        //Assert
        whenever(repository.getRepositoryPulls("owner/repository"))
            .thenReturn(Single.just(prList))
    }
}