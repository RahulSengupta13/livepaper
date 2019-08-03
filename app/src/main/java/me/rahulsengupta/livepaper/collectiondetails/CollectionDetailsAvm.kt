package me.rahulsengupta.livepaper.collectiondetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch
import me.rahulsengupta.livepaper.collectiondetails.api.CollectionDetailsApi
import me.rahulsengupta.livepaper.collectiondetails.models.CollectionDetailsViewModel
import me.rahulsengupta.livepaper.core.coroutine.ScopedViewModel

class CollectionDetailsAvm(val api: CollectionDetailsApi) : ScopedViewModel() {

    private val _logic: CollectionDetailsLogic

    private val _presentCollectionDetails = MutableLiveData<CollectionDetailsViewModel>()

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

    /**
     * Actionables
     * */
    fun setup(collectionId: Int) {
        coroutineScope.launch { _logic.setup(collectionId) }
    }
}