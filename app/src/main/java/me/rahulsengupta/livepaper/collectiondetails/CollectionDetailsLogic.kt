package me.rahulsengupta.livepaper.collectiondetails

import me.rahulsengupta.livepaper.collectiondetails.api.CollectionDetailsApi
import me.rahulsengupta.livepaper.collectiondetails.models.CollectionDetailsViewModel
import me.rahulsengupta.livepaper.collectiondetails.models.CollectionWallpaperViewModel
import me.rahulsengupta.livepaper.core.retrofit.RetrofitResult
import me.rahulsengupta.network.endpoints.unsplash.responses.CollectionDetails
import me.rahulsengupta.network.endpoints.unsplash.responses.CollectionWallpaper
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
                collectionDetails.description,
                collectionDetails.user?.image?.medium,
                collectionDetails.coverPhoto?.urls?.regular,
                collectionDetails.user?.instagramUsername,
                collectionDetails.user?.twitterUsername
            )
        }

        fun toWallpaperViewModel(response: List<CollectionWallpaper>): List<CollectionWallpaperViewModel> {
            return response
                .filter { it.urls.regular != null }
                .map {
                    CollectionWallpaperViewModel(it.urls.regular ?: "")
                }
        }
    }
}
