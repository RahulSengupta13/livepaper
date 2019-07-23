package me.rahulsengupta.livepaper.core.di

import me.rahulsengupta.livepaper.collections.CollectionsAvm
import me.rahulsengupta.livepaper.collections.api.CollectionsApi
import me.rahulsengupta.livepaper.collections.api.CollectionsApiImpl
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val collectionsModule = module {
    factory<CollectionsApi> {
        CollectionsApiImpl(get())
    }
    viewModel { CollectionsAvm(get(), get()) }
}