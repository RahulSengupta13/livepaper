package me.rahulsengupta.livepaper.collections

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import kotlinx.coroutines.launch
import me.rahulsengupta.livepaper.core.coroutine.ScopedViewModel
import me.rahulsengupta.livepaper.collections.api.CollectionsApi
import me.rahulsengupta.livepaper.collections.models.CollectionViewModel
import me.rahulsengupta.livepaper.collections.models.CollectionsDataSource.Companion.PAGE_SIZE
import me.rahulsengupta.livepaper.collections.models.CollectionsFactory

class CollectionsAvm(api: CollectionsApi) : ScopedViewModel() {

    private val _collectionsPagedListSource: MutableLiveData<PageKeyedDataSource<Int, CollectionViewModel>>
    private val _collectionsPagedList: LiveData<PagedList<CollectionViewModel>>
    private val _logic: CollectionsLogic

    init {
        val listener = object : CollectionsLogic.Listener {

        }
        _logic = CollectionsLogic(listener, api)

        val collectionsFactory = CollectionsFactory()
        _collectionsPagedListSource = collectionsFactory.getCollectionsSource()

        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(PAGE_SIZE)
            .build()
        _collectionsPagedList = LivePagedListBuilder(collectionsFactory, pagedListConfig).build()
    }

    /**
     * Observables
     * */
    fun collectionsPagedList() = _collectionsPagedList

    /**
     * Actionables
     * */
    fun setup() {
        coroutineScope.launch { _logic.setup() }
    }
}