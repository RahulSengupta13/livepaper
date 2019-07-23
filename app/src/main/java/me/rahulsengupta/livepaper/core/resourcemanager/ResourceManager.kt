package me.rahulsengupta.livepaper.core.resourcemanager

import android.content.Context
import androidx.annotation.StringRes

interface ResourceManager {
    fun getString(@StringRes resourceId: Int): String
    fun getString(@StringRes resourceId: Int, vararg formatArgs: Any?): String
}

class ResourceManagerImpl(val context: Context): ResourceManager {
    override fun getString(resourceId: Int): String = context.getString(resourceId)
    override fun getString(resourceId: Int, vararg formatArgs: Any?): String = context.getString(resourceId, *formatArgs)
}