package com.ibrajix.greyassessment.data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserRepositoryResponse(
    @Json(name = "name") val name: String? = "",
    @Json(name = "full_name") val fullName: String? = "",
    @Json(name = "private") val isPrivate: Boolean,
    @Json(name = "owner") val owner: Owner = Owner(),
    @Json(name = "html_url") val htmlUrl: String? = "",
    @Json(name = "description") val description: String? = "",
    @Json(name = "fork") val isFork: Boolean,
    @Json(name = "url") val url: String? = "",
    @Json(name = "created_at") val createdAt: String? = "",
    @Json(name = "updated_at") val updatedAt: String? = "",
    @Json(name = "pushed_at") val pushedAt: String? = "",
    @Json(name = "stargazers_count") val stargazersCount: Int? = 0,
    @Json(name = "language") val language: String? = "",
    @Json(name = "topics") val topics: List<String>? = emptyList(),
    @Json(name = "visibility") val visibility: String? = ""
)

@JsonClass(generateAdapter = true)
data class Owner(
    @Json(name = "login") val login: String? = "",
    @Json(name = "avatar_url") val avatarUrl: String? = "",
    @Json(name = "repos_url") val reposUrl: String? = ""
)
