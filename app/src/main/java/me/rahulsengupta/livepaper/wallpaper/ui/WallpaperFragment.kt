package me.rahulsengupta.livepaper.wallpaper.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import me.rahulsengupta.livepaper.R
import me.rahulsengupta.livepaper.wallpaper.models.WallpaperViewModel

class WallpaperFragment : Fragment(), WallpaperPresenter.Listener {

    private lateinit var presenterContainer: WallpaperPresenter.Container

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_wallpaper, container, false)
        presenterContainer = WallpaperPresenter.Container(root, this)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val safeArgs = WallpaperFragmentArgs.fromBundle(it)
            WallpaperPresenter.present(presenterContainer, WallpaperViewModel(safeArgs.wallpaperUrl))
        }
    }
}