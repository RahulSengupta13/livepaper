package me.rahulsengupta.livepaper.collections.models

import me.rahulsengupta.livepaper.collections.ui.CollectionsPagedAdapter

sealed class CollectionViewModel {
    abstract fun viewType(): CollectionsPagedAdapter.ViewType
    abstract fun uniqueId(): String
}

data class FeaturedCollectionViewModel(
    val collectionId: Int,
    val coverUrl: String,
    val collectionName: String?,
    val photoCount: Int?,
    val authorName: String?,
    val authorImageUrl: String
) : CollectionViewModel() {
    override fun viewType() = CollectionsPagedAdapter.ViewType.ITEM_FEATURED
    override fun uniqueId() = CollectionsPagedAdapter.ViewType.ITEM_FEATURED.name
}

data class NormalCollectionViewModel(
    val collectionId: Int,
    val coverUrl: String,
    val collectionName: String?,
    val photoCount: Int?,
    val authorName: String?,
    val authorImageUrl: String
) : CollectionViewModel() {
    override fun viewType() = CollectionsPagedAdapter.ViewType.ITEM_NORMAL
    override fun uniqueId() = CollectionsPagedAdapter.ViewType.ITEM_NORMAL.name
}