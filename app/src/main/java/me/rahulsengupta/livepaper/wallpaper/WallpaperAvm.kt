package me.rahulsengupta.livepaper.wallpaper

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch
import me.rahulsengupta.livepaper.core.coroutine.ScopedViewModel
import me.rahulsengupta.livepaper.wallpaper.api.WallpaperApi
import me.rahulsengupta.livepaper.wallpaper.models.WallpaperViewModel

class WallpaperAvm(wallpaperApi: WallpaperApi) : ScopedViewModel() {

    private val _logic: WallpaperLogic
    private val _present = MutableLiveData<WallpaperViewModel>()

    init {
        val listener = object: WallpaperLogic.Listener {
            override fun present(wallpaperViewModel: WallpaperViewModel) = _present.postValue(wallpaperViewModel)
        }

        _logic = WallpaperLogic(wallpaperApi, listener)
    }

    fun present() = _present

    fun setup(wallpaperId: String) {
        coroutineScope.launch {
            _logic.setup(wallpaperId)
        }
    }
}