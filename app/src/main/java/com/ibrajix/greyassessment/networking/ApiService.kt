package com.ibrajix.greyassessment.networking

import com.ibrajix.greyassessment.data.response.ErrorResponse
import com.ibrajix.greyassessment.data.response.NetworkResponse
import com.ibrajix.greyassessment.data.response.UserDetailsResponse
import com.ibrajix.greyassessment.data.response.UsersResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

typealias ApiResponse<S> = NetworkResponse<S, ErrorResponse>

interface ApiService {

    @GET("search/users")
    suspend fun getUsers(
        @Query("q") q: String,
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 10
    ): ApiResponse<UsersResponse>

    @GET("users/{name}")
    suspend fun getUserDetails(
        @Path("name") name: String,
    ): ApiResponse<UserDetailsResponse>
}