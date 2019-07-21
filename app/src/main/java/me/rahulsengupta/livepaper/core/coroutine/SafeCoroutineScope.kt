package me.rahulsengupta.livepaper.core.coroutine

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import me.rahulsengupta.livepaper.core.coroutine.BaseCoroutine
import kotlin.coroutines.CoroutineContext

open class SafeCoroutineScope(context: Context): BaseCoroutine(context) {

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = super.coroutineContext + job + Dispatchers.Default

}