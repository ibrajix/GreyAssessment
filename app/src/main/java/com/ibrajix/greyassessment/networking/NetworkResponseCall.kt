package com.ibrajix.greyassessment.networking

import com.ibrajix.greyassessment.data.response.EmptyResponse
import com.ibrajix.greyassessment.data.response.ErrorResponse
import com.ibrajix.greyassessment.data.response.NetworkResponse
import okhttp3.Request
import okhttp3.ResponseBody
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Converter
import retrofit2.Response
import java.net.UnknownHostException

const val NETWORK_EXCEPTION = "419"

class NetworkResponseCall<T, E : ErrorResponse>(
    private val call: Call<T>,
    private val errorConverter: Converter<ResponseBody, E>
) : Call<NetworkResponse<T, E>> {

    override fun enqueue(callback: Callback<NetworkResponse<T, E>>) {
        return call.enqueue(object : Callback<T> {
            override fun onFailure(call: Call<T>, t: Throwable) {

                val message = if (t is UnknownHostException) {
                    "It appears your network connectivity is down. Check your connection and try again"
                } else {
                    t.message ?: "An unexpected error occurred."
                }
                callback.onResponse(
                    this@NetworkResponseCall,
                    Response.success(
                        NetworkResponse.Failure(
                            ErrorResponse(
                                message = message,
                                status = NETWORK_EXCEPTION
                            )
                        )
                    )
                )
            }

            override fun onResponse(call: Call<T>, response: Response<T>) {
                val body = response.body()
                val error = response.errorBody()

                if (response.isSuccessful) {
                    if (body != null) {
                        callback.onResponse(
                            this@NetworkResponseCall,
                            Response.success(NetworkResponse.Success(body))
                        )
                    } else {

                        callback.onResponse(
                            this@NetworkResponseCall,
                            Response.success(NetworkResponse.Failure(EmptyResponse()))
                        )

                    }
                } else {

                    val errorBody = when {
                        error == null -> null
                        error.contentLength() == 0L -> null
                        else -> try {
                            errorConverter.convert(error).apply {
                                this?.status = response.code().toString()
                            }
                        } catch (ex: Exception) {
                            null
                        }
                    }

                    val errorResponse =
                        errorBody ?: ErrorResponse(message = "Unexpected Error. Please try again")

                    callback.onResponse(
                        this@NetworkResponseCall,
                        Response.success(NetworkResponse.Failure(errorResponse))
                    )
                }
            }
        })
    }

    override fun execute(): Response<NetworkResponse<T, E>> {
        throw UnsupportedOperationException("NetworkResponseCall doesn't support execute")
    }

    override fun isExecuted(): Boolean = call.isExecuted

    override fun timeout(): Timeout = call.timeout()

    override fun clone(): Call<NetworkResponse<T, E>> =
        NetworkResponseCall(call.clone(), errorConverter)

    override fun isCanceled(): Boolean = call.isCanceled
    override fun cancel() = call.cancel()
    override fun request(): Request = call.request()
}