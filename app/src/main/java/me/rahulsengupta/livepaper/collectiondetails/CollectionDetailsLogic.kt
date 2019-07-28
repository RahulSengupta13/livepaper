package me.rahulsengupta.livepaper.collectiondetails

import me.rahulsengupta.livepaper.collectiondetails.api.CollectionDetailsApi
import me.rahulsengupta.livepaper.core.retrofit.RetrofitResult
import timber.log.Timber

class CollectionDetailsLogic (val listener: Listener, val api: CollectionDetailsApi) {

    interface Listener

    fun setup(collectionId: Int) {
        api.getCollectionDetails(collectionId).run {
            when(this) {
                is RetrofitResult.SuccessfulResult -> {
                    Timber.d(this.toString())
                }
                is RetrofitResult.ErrorResult -> {
                    Timber.d(this.toString())
                }
            }
        }
    }
}