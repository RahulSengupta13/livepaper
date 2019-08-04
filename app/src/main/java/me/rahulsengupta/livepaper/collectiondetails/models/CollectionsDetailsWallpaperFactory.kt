package me.rahulsengupta.livepaper.collectiondetails.models

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource

class CollectionsDetailsWallpaperFactory(val collectionId: Int) : DataSource.Factory<Int, CollectionWallpaperViewModel>() {

    private val _collectionsSource = MutableLiveData<PageKeyedDataSource<Int, CollectionWallpaperViewModel>>()

    override fun create(): DataSource<Int, CollectionWallpaperViewModel> {
        val topHeadlinesDataSource = CollectionWallpapersDataSource(collectionId)
        _collectionsSource.postValue(topHeadlinesDataSource)
        return topHeadlinesDataSource
    }

    fun getCollectionsSource() = _collectionsSource
}