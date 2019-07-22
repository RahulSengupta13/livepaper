package me.rahulsengupta.livepaper.collections

import me.rahulsengupta.livepaper.core.retrofit.RetrofitResult
import me.rahulsengupta.livepaper.collections.api.CollectionsApi
import me.rahulsengupta.livepaper.collections.models.CollectionViewModel
import me.rahulsengupta.network.endpoints.unsplash.responses.Collection
import timber.log.Timber

class CollectionsLogic(val listener: Listener, private val api: CollectionsApi) {

    interface Listener

    fun setup() {
        api.getCollections(1, 10).run {
            when (this) {
                is RetrofitResult.SuccessfulResult -> {
                    Timber.d(body.toString())
                }
                is RetrofitResult.ErrorResult -> {
                    Timber.d(message)
                }
            }
        }
    }

    companion object {

        fun toCollectionsViewModel(collections: List<Collection>): List<CollectionViewModel> {
            return collections.map { CollectionViewModel(it.id, it.coverPhoto.urls?.regular ?: "") }
        }
    }
}