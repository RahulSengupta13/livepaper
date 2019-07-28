package me.rahulsengupta.livepaper.core.di

import me.rahulsengupta.livepaper.collectiondetails.CollectionDetailsAvm
import me.rahulsengupta.livepaper.collectiondetails.api.CollectionDetailsApi
import me.rahulsengupta.livepaper.collectiondetails.api.CollectionDetailsApiImpl
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val collectionDetailsModule = module {
    factory<CollectionDetailsApi> { CollectionDetailsApiImpl(get()) }
    viewModel { CollectionDetailsAvm(get()) }
}