package me.rahulsengupta.livepaper.collectiondetails.models

data class CollectionDetailsViewModel(
    val collectionName: String,
    val collectionAuthor: String?,
    val collectionDescription: String?,
    val collectionAuthorPhoto: String?,
    val collectionCoverPhoto: String?
)