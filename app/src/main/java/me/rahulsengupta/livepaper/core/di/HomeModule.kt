package me.rahulsengupta.livepaper.core.di

import me.rahulsengupta.livepaper.home.HomeFragmentAvm
import me.rahulsengupta.livepaper.home.api.HomeApi
import me.rahulsengupta.livepaper.home.api.HomeApiImpl
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    factory<HomeApi> { HomeApiImpl(get()) }
    viewModel { HomeFragmentAvm(get()) }
}