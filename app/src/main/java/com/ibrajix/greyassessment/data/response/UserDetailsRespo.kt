package com.ibrajix.greyassessment.data.response
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserDetailsResponse(
    @Json(name = "avatar_url")
    val avatarUrl: String? = "",
    @Json(name = "repos_url")
    val reposUrl: String? = "",
    @Json(name = "name")
    val name: String? = "",
    @Json(name = "login")
    val login: String? = "",
    @Json(name = "blog")
    val blog: String? = "",
    @Json(name = "location")
    val location: String? = "",
    @Json(name = "email")
    val email: String? = "",
    @Json(name = "bio")
    val bio: String? = "",
    @Json(name = "public_repos")
    val publicRepos: Int? = 0,
    @Json(name = "followers")
    val followers: Int? = 0,
    @Json(name = "following")
    val following: Int? = 0,
)