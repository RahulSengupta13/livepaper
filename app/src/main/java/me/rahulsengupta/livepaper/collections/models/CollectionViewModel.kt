package me.rahulsengupta.livepaper.collections.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import me.rahulsengupta.livepaper.collections.ui.CollectionsPagedAdapter
import java.io.Serializable

sealed class CollectionViewModel {
    abstract fun viewType(): CollectionsPagedAdapter.ViewType
    abstract fun uniqueId(): String
}

@Parcelize
data class FeaturedCollectionViewModel(
    val collectionId: Int,
    val coverUrl: String,
    val collectionName: String?,
    val collectionDescription: String?,
    val photoCount: Int?,
    val authorName: String?,
    val authorImageUrl: String,
    val publishedAt: String?
) : CollectionViewModel(), Parcelable, Serializable {
    override fun viewType() = CollectionsPagedAdapter.ViewType.ITEM_FEATURED
    override fun uniqueId() = CollectionsPagedAdapter.ViewType.ITEM_FEATURED.name
}

@Parcelize
data class NormalCollectionViewModel(
    val collectionId: Int,
    val coverUrl: String,
    val collectionName: String?,
    val photoCount: Int?,
    val authorName: String?,
    val authorImageUrl: String
) : CollectionViewModel(), Parcelable, Serializable {
    override fun viewType() = CollectionsPagedAdapter.ViewType.ITEM_NORMAL
    override fun uniqueId() = CollectionsPagedAdapter.ViewType.ITEM_NORMAL.name
}