package me.rahulsengupta.livepaper.wallpaper.ui

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.davemorrissey.labs.subscaleview.ImageSource
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView
import kotlinx.android.synthetic.main.fragment_wallpaper.view.*
import me.rahulsengupta.livepaper.wallpaper.models.WallpaperViewModel

class WallpaperPresenter {

    class Container(val root: View, val listener: Listener) {
        val wallpaper: SubsamplingScaleImageView = root.wallpaper_image
    }

    companion object {
        fun present(container: Container, viewModel: WallpaperViewModel) {
            container.run {
                Glide
                    .with(container.root)
                    .asBitmap()
                    .load(viewModel.wallpaperUrl)
                    .into(object: CustomTarget<Bitmap>() {
                        override fun onLoadCleared(placeholder: Drawable?) = Unit
                        override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                            wallpaper.setImage(ImageSource.bitmap(resource))
                        }
                    })

            }
        }
    }

    interface Listener
}