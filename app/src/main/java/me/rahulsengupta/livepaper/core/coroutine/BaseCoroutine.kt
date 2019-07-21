package me.rahulsengupta.livepaper.core.coroutine

import android.content.Context
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

open class BaseCoroutine(private val context: Context) : CoroutineScope {

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Timber.e(throwable)
    }

    override val coroutineContext: CoroutineContext
        get() = coroutineExceptionHandler

}