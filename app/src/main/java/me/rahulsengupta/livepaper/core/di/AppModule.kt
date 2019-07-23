package me.rahulsengupta.livepaper.core.di

import me.rahulsengupta.livepaper.core.resourcemanager.ResourceManager
import me.rahulsengupta.livepaper.core.resourcemanager.ResourceManagerImpl
import me.rahulsengupta.livepaper.core.retrofit.RetrofitFactory.createUnsplashRetrofitClient
import org.koin.dsl.module

val appModule = module {
    single<ResourceManager> { ResourceManagerImpl(get()) }
    single { createUnsplashRetrofitClient() }
}