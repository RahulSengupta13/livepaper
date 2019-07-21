package me.rahulsengupta.livepaper.core.retrofit

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import me.rahulsengupta.livepaper.BuildConfig
import me.rahulsengupta.network.endpoints.unsplash.UnsplashEndpoints
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {
    private const val BASE_URL_UNSPLASH = "https://api.unsplash.com/"

    fun createUnsplashRetrofitclient(): UnsplashEndpoints {
        val okHttpClient = OkHttpClient.Builder()
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val headerAuthorizationInterceptor = Interceptor { chain ->
            val request = chain.request()
            val originalHttpUrl = request.url()

            val url = originalHttpUrl
                .newBuilder()
                .build()

            val requestBuilder = request
                .newBuilder()
                .addHeader("Authorization:", "Client-ID ${BuildConfig.LivePaperUnsplashAccessKey}")
                .url(url)
            chain.proceed(requestBuilder.build())
        }
        okHttpClient.apply {
            addInterceptor(headerAuthorizationInterceptor)
            addInterceptor(loggingInterceptor)
        }

        return Retrofit.Builder()
            .baseUrl(BASE_URL_UNSPLASH)
            .client(okHttpClient.build())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().disableHtmlEscaping().create()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(UnsplashEndpoints::class.java)
    }
}