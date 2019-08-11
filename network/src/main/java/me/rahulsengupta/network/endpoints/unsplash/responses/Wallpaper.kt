package me.rahulsengupta.network.endpoints.unsplash.responses

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Wallpaper (
    val id: String,
    @SerializedName("updated_at")
    val updatedAt: String?,
    val downloads: Int,
    val likes: Int,
    val description: String?,
    val location: Location?,
    val tags: List<Tag>?,
    val urls: Urls,
    val user: Collection.User,
    val width: Int,
    val height: Int
): Serializable {

    data class Urls(
        val regular: String?,
        val full: String?
    ) : Serializable

    data class Tag(val title: String)

    data class Location(
        val city: String?,
        val country: String?,
        val position: Position?
    ): Serializable {

        data class Position(
            val latitude: String?,
            val longitude: String?
        )
    }
}