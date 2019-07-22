package me.rahulsengupta.livepaper.collections.api

import me.rahulsengupta.livepaper.core.retrofit.RetrofitResult
import me.rahulsengupta.livepaper.core.retrofit.getRetrofitResult
import me.rahulsengupta.network.endpoints.unsplash.UnsplashEndpoints
import me.rahulsengupta.network.endpoints.unsplash.responses.Collection

interface CollectionsApi {
    fun getCollections(page: Int, perPage: Int): RetrofitResult<List<Collection>>
}

class CollectionsApiImpl(private val api: UnsplashEndpoints) : CollectionsApi {
    override fun getCollections(page: Int, perPage: Int) = api.getCollections(page, perPage).getRetrofitResult()
}