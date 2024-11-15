package com.ibrajix.greyassessment.data.repo

import android.content.Context
import com.ibrajix.greyassessment.data.response.NetworkResponse
import com.ibrajix.greyassessment.data.response.RepositoryResponse
import com.ibrajix.greyassessment.data.response.UserDetailsResponse
import com.ibrajix.greyassessment.data.response.UserRepositoryResponse
import com.ibrajix.greyassessment.data.response.UsersResponse
import com.ibrajix.greyassessment.networking.ApiResponse
import com.ibrajix.greyassessment.networking.ApiService
import com.ibrajix.greyassessment.room.dao.UserDao
import com.ibrajix.greyassessment.room.entity.UserEntity
import com.ibrajix.greyassessment.util.isInternetAvailable
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class DataSource @Inject constructor(
    private val apiService: ApiService,
    private val userDao: UserDao,
    @ApplicationContext private val context: Context
) {
    private val coroutineContext: CoroutineContext = Dispatchers.IO


    suspend fun
            getUsers(q: String, page: Int, perPage: Int): List<UserEntity> {
        return if (isInternetAvailable(context = context)) {
            try {
                val response = apiService.getUsers(q = q, page = page, perPage = perPage)
                when (response) {
                    is NetworkResponse.Success -> {
                        val users = response.body.items.map { user ->
                            UserEntity(
                                login = user.login,
                                avatarUrl = user.avatarUrl
                            )
                        }
                        userDao.clearUsers()
                        userDao.insertUsers(users)
                        users
                    }
                    is NetworkResponse.Failure -> {
                        userDao.getAllUsers()
                    }
                }
            } catch (e: Exception) {
                userDao.getAllUsers()
            }
        } else {
            userDao.getAllUsers()
        }
    }

    suspend fun getUserDetails(name: String): ApiResponse<UserDetailsResponse> =
        withContext(coroutineContext) {
            apiService.getUserDetails(name = name)
        }

    suspend fun getUserRepos(name: String): ApiResponse<List<UserRepositoryResponse>> =
        withContext(coroutineContext) {
            apiService.getUserRepos(name = name)
        }

    suspend fun getRepositories(
        q: String,
        page: Int,
        perPage: Int
    ): ApiResponse<RepositoryResponse> =
        withContext(coroutineContext) {
            apiService.getRepos(q = q, page = page, perPage= perPage)
        }
}