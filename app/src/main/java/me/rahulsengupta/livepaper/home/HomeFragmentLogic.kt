package me.rahulsengupta.livepaper.home

import me.rahulsengupta.livepaper.core.retrofit.RetrofitResult
import me.rahulsengupta.livepaper.home.api.HomeApi
import timber.log.Timber

class HomeFragmentLogic(val listener: Listener, private val api: HomeApi) {

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
}