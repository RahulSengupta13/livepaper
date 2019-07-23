package me.rahulsengupta.livepaper.collections.models

data class CollectionViewModel(
    val collectionId: Int,
    val coverUrl: String,
    val collectionName: String?,
    val photoCount: Int?,
    val authorName: String?,
    val authorImageUrl: String
)