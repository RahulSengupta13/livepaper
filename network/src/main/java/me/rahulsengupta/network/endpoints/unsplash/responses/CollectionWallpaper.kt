package me.rahulsengupta.network.endpoints.unsplash.responses

import java.io.Serializable

data class CollectionWallpaper (
    val id: String,
    val urls: Urls
): Serializable {

    data class Urls(
        val regular: String?
    ) : Serializable
}