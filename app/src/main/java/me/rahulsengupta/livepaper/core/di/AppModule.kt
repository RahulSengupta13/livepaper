package me.rahulsengupta.livepaper.core.di

import me.rahulsengupta.livepaper.core.retrofit.RetrofitFactory.createUnsplashRetrofitClient
import org.koin.dsl.module

val appModule = module {
    single { createUnsplashRetrofitClient() }
}