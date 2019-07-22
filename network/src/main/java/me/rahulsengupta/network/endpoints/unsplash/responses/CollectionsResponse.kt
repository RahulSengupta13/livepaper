package me.rahulsengupta.network.endpoints.unsplash.responses

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Collection(
    val id: Int,
    val title: String?,
    val description: String?,
    @SerializedName("cover_photo")
    val coverPhoto: CoverPhoto
) : Serializable {

    data class CoverPhoto(
        val urls: Urls?
    ) : Serializable {

        data class Urls(
            val regular: String?
        ) : Serializable
    }
}