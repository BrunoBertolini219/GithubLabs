package com.brunoccbertolini.github.data.remote.mapper

import com.brunoccbertolini.github.data.GithubMock
import org.junit.Assert
import org.junit.Test

class RepositoryListMapperTest {
    private val mockModel = GithubMock()
    @Test
    fun `GIVEN repository list request THEN should return repository list model`() {
        //Arrange
        val repositoryListResponse = mockModel.githubRepoListResponse
        val repositoryListModel = mockModel.githubRepoListModel

        //Assert
        Assert.assertEquals(
            repositoryListResponse.mapToRepoListModel(),
            repositoryListModel
        )
    }
}