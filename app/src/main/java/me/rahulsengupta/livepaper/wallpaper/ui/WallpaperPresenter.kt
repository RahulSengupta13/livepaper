package me.rahulsengupta.livepaper.wallpaper.ui

import android.view.View
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView
import kotlinx.android.synthetic.main.fragment_wallpaper.view.*
import me.rahulsengupta.livepaper.core.utils.setImageAndAnimate
import me.rahulsengupta.livepaper.wallpaper.models.WallpaperViewModel

class WallpaperPresenter {

    class Container(val root: View, val listener: Listener) {
        val wallpaper: SubsamplingScaleImageView = root.wallpaper_image

        init {
            wallpaper.setPanLimit(SubsamplingScaleImageView.PAN_LIMIT_INSIDE)
            wallpaper.setMinimumScaleType(SubsamplingScaleImageView.SCALE_TYPE_CENTER_CROP)
            wallpaper.animateScale(3f)?.start()
        }
    }

    companion object {
        fun present(container: Container, viewModel: WallpaperViewModel) {
            container.run {
                wallpaper.setImageAndAnimate(viewModel.wallpaperDisplayUrl)
            }
        }
    }

    interface Listener
}