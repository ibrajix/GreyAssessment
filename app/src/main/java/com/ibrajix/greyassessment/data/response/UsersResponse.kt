package com.ibrajix.greyassessment.data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UsersResponse(
    @Json(name = "items")
    val items: List<User>
)
@JsonClass(generateAdapter = true)
data class User(
    @Json(name = "id")
    val id: String,
    @Json(name = "avatar_url")
    val avatarUrl: String
)