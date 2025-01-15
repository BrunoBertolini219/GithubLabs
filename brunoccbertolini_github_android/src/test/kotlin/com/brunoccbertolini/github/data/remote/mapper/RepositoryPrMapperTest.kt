package com.brunoccbertolini.github.data.remote.mapper

import com.brunoccbertolini.github.data.GithubMock
import org.junit.Assert
import org.junit.Test

class RepositoryPrMapperTest {
    private val mockModel = GithubMock()
    @Test
    fun `GIVEN repository prs list request THEN should return repository prs list model`() {
        //Arrange
        val repositoryPrListResponse = mockModel.githubPrListResponse
        val repositoryPrListModel = mockModel.githubPrListModel

        //Assert
        Assert.assertEquals(
            repositoryPrListResponse.mapToRepositoryListPrModel(),
            repositoryPrListModel
        )
    }
}