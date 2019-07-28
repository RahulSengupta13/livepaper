package me.rahulsengupta.livepaper.collectiondetails.api

import me.rahulsengupta.livepaper.core.retrofit.RetrofitResult
import me.rahulsengupta.livepaper.core.retrofit.getRetrofitResult
import me.rahulsengupta.network.endpoints.unsplash.UnsplashEndpoints
import me.rahulsengupta.network.endpoints.unsplash.responses.CollectionDetails

interface CollectionDetailsApi {
    fun getCollectionDetails(collectionId: Int): RetrofitResult<CollectionDetails>
}

class CollectionDetailsApiImpl(private val api: UnsplashEndpoints) : CollectionDetailsApi {
    override fun getCollectionDetails(collectionId: Int) = api.getCollectionDetails(collectionId).getRetrofitResult()
}