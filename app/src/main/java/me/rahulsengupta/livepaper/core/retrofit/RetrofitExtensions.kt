package me.rahulsengupta.livepaper.core.retrofit

import me.rahulsengupta.network.responses.NoContentResponse
import okhttp3.Headers
import retrofit2.Call
import retrofit2.Response
import timber.log.Timber
import java.net.HttpURLConnection

fun <T> Call<T>.getRetrofitResult(defaultErrorMessage: String = "Error"): RetrofitResult<T> {
    return try {
        val response = execute()
        if (isSuccessful(response.code())) {
            val body = response.body()
            val headers = response.headers()
            when {
                body != null -> RetrofitResult.SuccessfulResult(body, headers)
                response.code() == HttpURLConnection.HTTP_NO_CONTENT -> RetrofitResult.SuccessfulResult(
                    NoContentResponse() as T,
                    headers
                )
                else -> {
                    toRetrofitResult(defaultErrorMessage, response)
                }
            }
        } else {
            val errorString = response.errorBody()?.string()
            toRetrofitResult(errorString ?: defaultErrorMessage, response)
        }
    } catch (e: Exception) {
        Timber.e(e)
        toRetrofitResult(defaultErrorMessage, null)
    }
}

private fun isSuccessful(code: Int): Boolean {
    return code in 200..299
}

private fun <R> toRetrofitResult(
    defaultErrorMessage: String = "",
    response: Response<*>?
): RetrofitResult.ErrorResult<R> {
    return RetrofitResult.ErrorResult(defaultErrorMessage, response)
}

fun Headers.getTotalItems(): Int {
    val totalItems = get("x-total")?.toInt()
    return totalItems ?: 0
}