package me.rahulsengupta.livepaper.collectiondetails.models

import androidx.paging.PageKeyedDataSource
import me.rahulsengupta.livepaper.collectiondetails.CollectionDetailsLogic.Companion.toWallpaperViewModel
import me.rahulsengupta.livepaper.collectiondetails.api.CollectionDetailsApi
import me.rahulsengupta.livepaper.core.retrofit.RetrofitResult
import me.rahulsengupta.livepaper.core.retrofit.getTotalItems
import org.koin.core.KoinComponent
import org.koin.core.inject

class CollectionWallpapersDataSource(val collectionId: Int) : PageKeyedDataSource<Int, CollectionWallpaperViewModel>(), KoinComponent {

    private val api: CollectionDetailsApi by inject()

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, CollectionWallpaperViewModel>) {
        api.getCollectionWallpapers(
            collectionId,
            INITIAL_PAGE,
            PAGE_SIZE
        ).run {
            when (this) {
                is RetrofitResult.SuccessfulResult -> {
                    callback.onResult(toWallpaperViewModel(body), null, INITIAL_PAGE + 1)
                }
                is RetrofitResult.ErrorResult -> Unit
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, CollectionWallpaperViewModel>) {
        api.getCollectionWallpapers(
            collectionId,
            params.key,
            PAGE_SIZE
        ).run {
            when (this) {
                is RetrofitResult.SuccessfulResult -> {
                    val adjacentKey = if (params.key * PAGE_SIZE < headers.getTotalItems()) params.key + 1 else null
                    callback.onResult(toWallpaperViewModel(body), adjacentKey)
                }
                is RetrofitResult.ErrorResult -> Unit
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, CollectionWallpaperViewModel>) {
        api.getCollectionWallpapers(
            collectionId,
            params.key,
            PAGE_SIZE
        ).run {
            when (this) {
                is RetrofitResult.SuccessfulResult -> {
                    val adjacentKey = if (params.key > 1) params.key - 1 else null
                    callback.onResult(toWallpaperViewModel(body), adjacentKey)
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