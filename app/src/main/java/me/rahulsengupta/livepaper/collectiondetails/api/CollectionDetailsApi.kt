package me.rahulsengupta.livepaper.collectiondetails.api

import me.rahulsengupta.livepaper.core.retrofit.RetrofitResult
import me.rahulsengupta.livepaper.core.retrofit.getRetrofitResult
import me.rahulsengupta.network.endpoints.unsplash.UnsplashEndpoints
import me.rahulsengupta.network.endpoints.unsplash.responses.CollectionDetails
import me.rahulsengupta.network.endpoints.unsplash.responses.CollectionWallpaper

interface CollectionDetailsApi {
    fun getCollectionDetails(collectionId: Int): RetrofitResult<CollectionDetails>
    fun getCollectionWallpapers(collectionId: Int, page: Int, perPage: Int): RetrofitResult<List<CollectionWallpaper>>
}

class CollectionDetailsApiImpl(private val api: UnsplashEndpoints) : CollectionDetailsApi {
    override fun getCollectionWallpapers(collectionId: Int, page: Int, perPage: Int) =
        api.getCollectionPhotos(collectionId, page, perPage).getRetrofitResult()

    override fun getCollectionDetails(collectionId: Int) =
        api.getCollectionDetails(collectionId).getRetrofitResult()
}