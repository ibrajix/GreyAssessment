package com.ibrajix.greyassessment.networking


import android.util.Log
import com.ibrajix.greyassessment.data.response.ErrorResponse
import com.ibrajix.greyassessment.data.response.NetworkResponse
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class NetworkResponseCallAdapterFactory: CallAdapter.Factory() {

    @ExperimentalStdlibApi
    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {

        if (returnType !is ParameterizedType) {
            throw IllegalStateException(
                "Response return type must be parameterized"
                        + " as NetworkResponse<Foo, Bar> or NetworkResponse<? extends Foo, ? extends Bar>"
            )
        }

        val responseType = getParameterUpperBound(0, returnType)

        if (getRawType(responseType) != NetworkResponse::class.java) return null

        Log.d("prmtmzd",getRawType(responseType).name)

        val successType = getParameterUpperBound(0, responseType as ParameterizedType)
        val errorBodyType = getParameterUpperBound(1, responseType)

        val errorBodyConverter =
            retrofit.nextResponseBodyConverter<ErrorResponse>(null, errorBodyType, annotations)

        return NetworkResponseCallAdapter<Any, ErrorResponse>(successType, errorBodyConverter)
    }
}