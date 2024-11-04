package com.ibrajix.greyassessment.data.repo

import com.ibrajix.greyassessment.data.response.*
import com.ibrajix.greyassessment.networking.ApiResponse
import com.ibrajix.greyassessment.networking.ApiService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DataSourceTest {

    // Mock ApiService
    private val mockApiService: ApiService = mockk()
    private lateinit var dataSource: DataSource

    @Before
    fun setUp() {
        dataSource = DataSource(apiService = mockApiService)
    }

    @Test
    fun `getUsers should return expected response`() = runTest {
        // Arrange
        val query = "John"
        val page = 1
        val perPage = 10
        val expectedResponse: ApiResponse<UsersResponse> = NetworkResponse.Success(
            UsersResponse(items = listOf())
        )

        coEvery { mockApiService.getUsers(query, page, perPage) } returns expectedResponse

        // Act
        val result = dataSource.getUsers(query, page, perPage)

        // Assert
        assertEquals(expectedResponse, result)
    }

    @Test
    fun `getUserDetails should return expected response`() = runTest {
        // Arrange
        val username = "john_doe"
        val expectedResponse: ApiResponse<UserDetailsResponse> = NetworkResponse.Success(
            UserDetailsResponse(name = "John Doe", bio = "A sample bio", followers = 100, following = 10)
        )

        coEvery { mockApiService.getUserDetails(username) } returns expectedResponse

        // Act
        val result = dataSource.getUserDetails(username)

        // Assert
        assertEquals(expectedResponse, result)
    }

    @Test
    fun `getUserRepos should return expected response`() = runTest {
        // Arrange
        val username = "john_doe"
        val expectedResponse: ApiResponse<List<UserRepositoryResponse>> = NetworkResponse.Success(
            listOf(UserRepositoryResponse(name = "Repo1", fullName = "john_doe/Repo1", description = "Test repo", isFork = false, isPrivate = false))
        )

        coEvery { mockApiService.getUserRepos(username) } returns expectedResponse

        // Act
        val result = dataSource.getUserRepos(username)

        // Assert
        assertEquals(expectedResponse, result)
    }

    @Test
    fun `getRepositories should return expected response`() = runTest {
        // Arrange
        val query = "android"
        val page = 1
        val perPage = 10
        val expectedResponse: ApiResponse<RepositoryResponse> = NetworkResponse.Success(
            RepositoryResponse(items = listOf())
        )

        coEvery { mockApiService.getRepos(query, page, perPage) } returns expectedResponse

        // Act
        val result = dataSource.getRepositories(query, page, perPage)

        // Assert
        assertEquals(expectedResponse, result)
    }
}
