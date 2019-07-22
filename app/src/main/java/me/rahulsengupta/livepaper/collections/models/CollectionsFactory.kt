package me.rahulsengupta.livepaper.collections.models

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import org.koin.core.KoinComponent

class CollectionsFactory : DataSource.Factory<Int, CollectionViewModel>(), KoinComponent {

    private val _collectionsSource = MutableLiveData<PageKeyedDataSource<Int, CollectionViewModel>>()

    override fun create(): DataSource<Int, CollectionViewModel> {
        val topHeadlinesDataSource = CollectionsDataSource()
        _collectionsSource.postValue(topHeadlinesDataSource)
        return topHeadlinesDataSource
    }

    fun getCollectionsSource() = _collectionsSource
}