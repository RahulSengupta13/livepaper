package me.rahulsengupta.livepaper.core.di

import me.rahulsengupta.livepaper.wallpaper.WallpaperAvm
import me.rahulsengupta.livepaper.wallpaper.api.WallpaperApi
import me.rahulsengupta.livepaper.wallpaper.api.WallpaperApiImpl
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val wallpaperModule = module {
    factory<WallpaperApi> {
        WallpaperApiImpl(get())
    }
    viewModel { WallpaperAvm(get()) }
}