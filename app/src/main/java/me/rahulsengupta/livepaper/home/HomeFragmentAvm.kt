package me.rahulsengupta.livepaper.home

import kotlinx.coroutines.launch
import me.rahulsengupta.livepaper.core.coroutine.ScopedViewModel
import me.rahulsengupta.livepaper.home.api.HomeApi

class HomeFragmentAvm(api: HomeApi) : ScopedViewModel() {

    private val _logic: HomeFragmentLogic

    init {
        val listener = object : HomeFragmentLogic.Listener {

        }
        _logic = HomeFragmentLogic(listener, api)
    }

    fun setup() {
        coroutineScope.launch { _logic.setup() }
    }
}