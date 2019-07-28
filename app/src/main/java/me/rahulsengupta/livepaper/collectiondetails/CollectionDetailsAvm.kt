package me.rahulsengupta.livepaper.collectiondetails

import kotlinx.coroutines.launch
import me.rahulsengupta.livepaper.collectiondetails.api.CollectionDetailsApi
import me.rahulsengupta.livepaper.core.coroutine.ScopedViewModel

class CollectionDetailsAvm(val api: CollectionDetailsApi) : ScopedViewModel() {

    private val _logic: CollectionDetailsLogic

    init {
        val listener = object: CollectionDetailsLogic.Listener {

        }
        _logic = CollectionDetailsLogic(listener, api)
    }

    /**
     * Observables
     * */

    /**
     * Actionables
     * */
    fun setup(collectionId: Int) {
        coroutineScope.launch { _logic.setup(collectionId) }
    }
}