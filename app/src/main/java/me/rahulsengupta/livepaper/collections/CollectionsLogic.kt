package me.rahulsengupta.livepaper.collections

import me.rahulsengupta.livepaper.collections.api.CollectionsApi
import me.rahulsengupta.livepaper.collections.models.CollectionViewModel
import me.rahulsengupta.livepaper.collections.models.FeaturedCollectionViewModel
import me.rahulsengupta.livepaper.core.resourcemanager.ResourceManager
import me.rahulsengupta.network.endpoints.unsplash.responses.Collection
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter

class CollectionsLogic(
    val listener: Listener,
    private val api: CollectionsApi,
    val resourceManager: ResourceManager
) {

    interface Listener

    companion object {
        fun toCollectionsViewModel(collections: List<Collection>): List<CollectionViewModel> {
            return collections.map {
                FeaturedCollectionViewModel(
                    it.id,
                    it.coverPhoto.urls?.regular ?: "",
                    it.title,
                    it.description,
                    it.totalPhotos,
                    it.user?.name,
                    it.user?.image?.medium ?: "",
                    DateTime.parse(it.publishedAt ?: "").toString(DateTimeFormat.longDate()) ?: ""
                )
            }
        }
    }
}