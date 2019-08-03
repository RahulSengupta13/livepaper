package me.rahulsengupta.network.endpoints.unsplash.responses

import com.google.gson.annotations.SerializedName
import me.rahulsengupta.network.endpoints.unsplash.responses.Collection.*
import java.io.Serializable

data class CollectionDetails(
    val id: Int,
    val title: String,
    val description: String?,
    val updatedAt: String?,
    val curated: Boolean?,
    val featured: Boolean?,
    @SerializedName("total_photos")
    val totalPhotos: Int?,
    @SerializedName("cover_photo")
    val coverPhoto: CoverPhoto?,
    val user: User?
) : Serializable