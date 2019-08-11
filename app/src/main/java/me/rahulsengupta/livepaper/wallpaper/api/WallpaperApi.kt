package me.rahulsengupta.livepaper.wallpaper.api

import me.rahulsengupta.livepaper.core.retrofit.RetrofitResult
import me.rahulsengupta.livepaper.core.retrofit.getRetrofitResult
import me.rahulsengupta.network.endpoints.unsplash.UnsplashEndpoints
import me.rahulsengupta.network.endpoints.unsplash.responses.Wallpaper

interface WallpaperApi {
    fun getWallpaper(id: String): RetrofitResult<Wallpaper>
}

class WallpaperApiImpl(val api: UnsplashEndpoints) : WallpaperApi {
    override fun getWallpaper(id: String) = api.getWallpaper(id).getRetrofitResult()
}