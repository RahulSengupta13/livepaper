package me.rahulsengupta.livepaper.wallpaper

import me.rahulsengupta.livepaper.core.retrofit.RetrofitResult
import me.rahulsengupta.livepaper.wallpaper.api.WallpaperApi
import me.rahulsengupta.livepaper.wallpaper.models.WallpaperViewModel
import me.rahulsengupta.network.endpoints.unsplash.responses.Wallpaper

class WallpaperLogic (val api: WallpaperApi, val listener: Listener) {

    interface Listener {
        fun present(wallpaperViewModel: WallpaperViewModel)
    }

    fun setup(wallpaperId: String) {
        when(val response = api.getWallpaper(wallpaperId)) {
            is RetrofitResult.SuccessfulResult -> {
                listener.present(responseToViewModel(response.body))
            }
            is RetrofitResult.ErrorResult -> Unit
        }
    }

    companion object {

        fun responseToViewModel(response: Wallpaper): WallpaperViewModel {
            return WallpaperViewModel(
                response.urls.regular ?: "",
                response.width.toFloat()/2,
                response.height.toFloat()/2
            )
        }
    }
}