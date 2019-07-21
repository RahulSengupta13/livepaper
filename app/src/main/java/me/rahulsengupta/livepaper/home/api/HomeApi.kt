package me.rahulsengupta.livepaper.home.api

import me.rahulsengupta.livepaper.core.retrofit.RetrofitResult
import me.rahulsengupta.livepaper.core.retrofit.getRetrofitResult
import me.rahulsengupta.network.endpoints.unsplash.UnsplashEndpoints
import me.rahulsengupta.network.endpoints.unsplash.responses.Collections

interface HomeApi {
    fun getCollections(page: Int, perPage: Int): RetrofitResult<List<Collections>>
}

class HomeApiImpl(private val api: UnsplashEndpoints) : HomeApi {
    override fun getCollections(page: Int, perPage: Int) = api.getCollections(page, perPage).getRetrofitResult()
}