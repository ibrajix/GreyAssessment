package com.ibrajix.greyassessment.networking


import com.ibrajix.greyassessment.data.response.ErrorResponse
import com.ibrajix.greyassessment.data.response.NetworkResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Converter
import java.lang.reflect.Type

class NetworkResponseCallAdapter<S: Any, U: ErrorResponse> (
    private val successType: Type,
    private val errorBodyConverter: Converter<ResponseBody, U>
): CallAdapter<S, Call<NetworkResponse<S, U>>> {

    override fun adapt(call: Call<S>): Call<NetworkResponse<S, U>> =
        NetworkResponseCall(call, errorBodyConverter)

    override fun responseType(): Type = successType
}