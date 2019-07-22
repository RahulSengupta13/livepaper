package me.rahulsengupta.livepaper.collections.models

import androidx.paging.PageKeyedDataSource
import me.rahulsengupta.livepaper.collections.CollectionsLogic.Companion.toCollectionsViewModel
import me.rahulsengupta.livepaper.collections.api.CollectionsApi
import me.rahulsengupta.livepaper.core.retrofit.RetrofitResult
import me.rahulsengupta.livepaper.core.retrofit.getTotalItems
import org.koin.core.KoinComponent
import org.koin.core.inject

class CollectionsDataSource : PageKeyedDataSource<Int, CollectionViewModel>(), KoinComponent {

    private val api: CollectionsApi by inject()

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, CollectionViewModel>) {
        api.getCollections(
            INITIAL_PAGE,
            PAGE_SIZE
        ).run {
            when (this) {
                is RetrofitResult.SuccessfulResult -> {
                    callback.onResult(toCollectionsViewModel(body), null, INITIAL_PAGE + 1)
                }
                is RetrofitResult.ErrorResult -> Unit
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, CollectionViewModel>) {
        api.getCollections(params.key,
            PAGE_SIZE
        ).run {
            when (this) {
                is RetrofitResult.SuccessfulResult -> {
                    val adjacentKey = if (params.key * PAGE_SIZE < headers.getTotalItems()) params.key + 1 else null
                    callback.onResult(toCollectionsViewModel(body), adjacentKey)
                }
                is RetrofitResult.ErrorResult -> Unit
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, CollectionViewModel>) {
        api.getCollections(params.key,
            PAGE_SIZE
        ).run {
            when (this) {
                is RetrofitResult.SuccessfulResult -> {
                    val adjacentKey = if (params.key > 1) params.key - 1 else null
                    callback.onResult(toCollectionsViewModel(body), adjacentKey)
                }
                is RetrofitResult.ErrorResult -> Unit
            }
        }
    }

    companion object {
        private const val INITIAL_PAGE = 1
        const val PAGE_SIZE = 25
    }
}