package me.rahulsengupta.livepaper.core.application

import android.app.Application
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
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

        val picasso = Picasso.Builder(this)
            .downloader(OkHttp3Downloader(this, Integer.MAX_VALUE.toLong()))
            .build()
        Picasso.setSingletonInstance(picasso)

        startKoin {
            androidLogger(level = Level.INFO)
            androidContext(this@LivePaperApplication)
            modules(listOf(appModule, collectionsModule))
        }
    }
}