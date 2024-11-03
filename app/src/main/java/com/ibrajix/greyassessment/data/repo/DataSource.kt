package com.ibrajix.greyassessment.data.repo

import com.ibrajix.greyassessment.data.response.User
import com.ibrajix.greyassessment.data.response.UserDetailsResponse
import com.ibrajix.greyassessment.data.response.UsersResponse
import com.ibrajix.greyassessment.networking.ApiResponse
import com.ibrajix.greyassessment.networking.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class DataSource @Inject constructor(
    private val apiService: ApiService
) {
    private val coroutineContext: CoroutineContext = Dispatchers.IO

    suspend fun getUsers(
        q: String,
        page: Int,
        perPage: Int
    ): ApiResponse<UsersResponse> =
        withContext(coroutineContext) {
            apiService.getUsers(q = q, page = page, perPage= perPage)
        }

    suspend fun getUserDetails(name: String): ApiResponse<UserDetailsResponse> =
        withContext(coroutineContext) {
            apiService.getUserDetails(name = name)
        }
}