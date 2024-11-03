package com.ibrajix.greyassessment.data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


sealed class NetworkResponse<T, U : ErrorResponse> {
    data class Success<T, U : ErrorResponse>(val body: T) : NetworkResponse<T, U>()
    data class Failure<T, U : ErrorResponse>(val error: ErrorResponse) : NetworkResponse<T, U>()
}

@JsonClass(generateAdapter = true)
open class ErrorResponse(
    @Json(name = "message") open val message: String,
    @Json(name = "status") open val status: String? = null,
)

class EmptyResponse : ErrorResponse(message="", status = "")