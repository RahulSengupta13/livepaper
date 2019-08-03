package me.rahulsengupta.livepaper.collectiondetails

import me.rahulsengupta.livepaper.collectiondetails.api.CollectionDetailsApi
import me.rahulsengupta.livepaper.collectiondetails.models.CollectionDetailsViewModel
import me.rahulsengupta.livepaper.core.retrofit.RetrofitResult
import me.rahulsengupta.network.endpoints.unsplash.responses.CollectionDetails
import timber.log.Timber

class CollectionDetailsLogic(val listener: Listener, val api: CollectionDetailsApi) {

    interface Listener {
        fun presentCollectionDetails(viewModel: CollectionDetailsViewModel)
    }

    fun setup(collectionId: Int) {
        api.getCollectionDetails(collectionId).run {
            when (this) {
                is RetrofitResult.SuccessfulResult -> {
                    listener.presentCollectionDetails(responseToViewModel(body))
                }
                is RetrofitResult.ErrorResult -> {
                    Timber.d(this.toString())
                }
            }
        }
    }

    companion object {
        fun responseToViewModel(collectionDetails: CollectionDetails): CollectionDetailsViewModel {
            return CollectionDetailsViewModel(
                collectionDetails.title,
                collectionDetails.user?.name,
                collectionDetails.coverPhoto?.urls?.regular
            )
        }
    }
}