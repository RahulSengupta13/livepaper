package me.rahulsengupta.livepaper.collections

import me.rahulsengupta.livepaper.collections.api.CollectionsApi
import me.rahulsengupta.livepaper.collections.models.CollectionViewModel
import me.rahulsengupta.network.endpoints.unsplash.responses.Collection

class CollectionsLogic(val listener: Listener, private val api: CollectionsApi) {

    interface Listener

    companion object {
        fun toCollectionsViewModel(collections: List<Collection>): List<CollectionViewModel> {
            return collections.map { CollectionViewModel(it.id, it.coverPhoto.urls?.regular ?: "") }
        }
    }
}