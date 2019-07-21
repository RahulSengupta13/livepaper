package me.rahulsengupta.livepaper.core.di

import me.rahulsengupta.livepaper.core.retrofit.RetrofitFactory.createUnsplashRetrofitclient
import org.koin.dsl.module

val appModule = module {
    single { createUnsplashRetrofitclient() }
}