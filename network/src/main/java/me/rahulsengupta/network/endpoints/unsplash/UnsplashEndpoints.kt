package me.rahulsengupta.network.endpoints.unsplash

import me.rahulsengupta.network.endpoints.unsplash.responses.Collection
import me.rahulsengupta.network.endpoints.unsplash.responses.CollectionDetails
import me.rahulsengupta.network.endpoints.unsplash.responses.CollectionWallpaper
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UnsplashEndpoints {

    @GET("collections")
    fun getCollections(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Call<List<Collection>>

    @GET("/collections/{collectionId}")
    fun getCollectionDetails(
        @Path("collectionId") collectionId: Int
    ): Call<CollectionDetails>

    @GET("/collections/{collectionId}/photos")
    fun getCollectionPhotos(
        @Path("collectionId") collectionId: Int,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Call<List<CollectionWallpaper>>
}