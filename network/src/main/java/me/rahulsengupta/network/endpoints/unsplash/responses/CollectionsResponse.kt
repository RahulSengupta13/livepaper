package me.rahulsengupta.network.endpoints.unsplash.responses

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Collection(
    val id: Int,
    val title: String?,
    val description: String?,
    val user: User?,
    @SerializedName("cover_photo")
    val coverPhoto: CoverPhoto,
    @SerializedName("published_at")
    val publishedAt: String?,
    @SerializedName("total_photos")
    val totalPhotos: Int?
) : Serializable {

    data class CoverPhoto(
        val urls: Urls?
    ) : Serializable {

        data class Urls(
            val regular: String?
        ) : Serializable
    }

    data class User(
        val name: String?,
        @SerializedName("profile_image")
        val image: UserImage?
    ) : Serializable {

        data class UserImage(
            val medium: String?,
            val large: String?
        ) : Serializable
    }
}