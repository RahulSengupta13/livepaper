package me.rahulsengupta.livepaper.core.application

import android.app.Application
import me.rahulsengupta.livepaper.core.di.appModule
import me.rahulsengupta.livepaper.core.di.collectionsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class LivePaperApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        startKoin {
            androidLogger(level = Level.INFO)
            androidContext(this@LivePaperApplication)
            modules(listOf(appModule, collectionsModule))
        }
    }
}