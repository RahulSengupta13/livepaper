package me.rahulsengupta.livepaper.core.coroutine

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import timber.log.Timber

open class ScopedViewModel : ViewModel() {

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Timber.e(throwable)
    }

    private val job = Job()

    protected val coroutineScope: CoroutineScope
        get() = CoroutineScope(job + coroutineExceptionHandler + Dispatchers.IO)

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}