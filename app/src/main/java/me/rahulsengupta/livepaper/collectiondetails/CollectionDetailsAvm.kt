package me.rahulsengupta.livepaper.collectiondetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import kotlinx.coroutines.launch
import me.rahulsengupta.livepaper.collectiondetails.api.CollectionDetailsApi
import me.rahulsengupta.livepaper.collectiondetails.models.CollectionDetailsViewModel
import me.rahulsengupta.livepaper.collectiondetails.models.CollectionWallpaperViewModel
import me.rahulsengupta.livepaper.collectiondetails.models.CollectionWallpapersDataSource.Companion.PAGE_SIZE
import me.rahulsengupta.livepaper.collectiondetails.models.CollectionsDetailsWallpaperFactory
import me.rahulsengupta.livepaper.core.coroutine.ScopedViewModel

class CollectionDetailsAvm(val api: CollectionDetailsApi) : ScopedViewModel() {

    private val _logic: CollectionDetailsLogic

    private val _presentCollectionDetails = MutableLiveData<CollectionDetailsViewModel>()
    private lateinit var _collectionWallpaperPagedListSource: MutableLiveData<PageKeyedDataSource<Int, CollectionWallpaperViewModel>>
    private lateinit var _collectionWallpaperPagedList: LiveData<PagedList<CollectionWallpaperViewModel>>

    init {
        val listener = object : CollectionDetailsLogic.Listener {
            override fun presentCollectionDetails(viewModel: CollectionDetailsViewModel) =
                _presentCollectionDetails.postValue(viewModel)
        }
        _logic = CollectionDetailsLogic(listener, api)
    }

    /**
     * Observables
     * */
    fun presentCollectionDetails(): LiveData<CollectionDetailsViewModel> = _presentCollectionDetails
    fun collectionWallpaperPagedList() = _collectionWallpaperPagedList

    /**
     * Actionables
     * */
    fun setup(collectionId: Int) {

        val collectionsFactory = CollectionsDetailsWallpaperFactory(collectionId)
        _collectionWallpaperPagedListSource = collectionsFactory.getCollectionsSource()

        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(PAGE_SIZE)
            .build()
        _collectionWallpaperPagedList = LivePagedListBuilder(collectionsFactory, pagedListConfig).build()

        coroutineScope.launch { _logic.setup(collectionId) }
    }
}