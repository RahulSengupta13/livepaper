package me.rahulsengupta.livepaper.core.utils

import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Bitmap
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target

class MaterialFadeTarget(
    private val imageView: ImageView,
    private val imageUrl: String,
    private val context: Context
) : Target {
    override fun onBitmapFailed(e: java.lang.Exception?, errorDrawable: Drawable?) {
        imageView.setImageDrawable(errorDrawable)
    }

    init {
        this.imageView.tag = this
    }

    override fun onBitmapLoaded(bitmap: Bitmap, from: Picasso.LoadedFrom) {
        val matrix = ColorMatrix()
        if (from != Picasso.LoadedFrom.MEMORY) {
            imageView.clearAnimation()
            val alphaOut = AlphaAnimation(1f, 0f)
            alphaOut.interpolator = AccelerateDecelerateInterpolator()
            alphaOut.duration = 200
            alphaOut.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation) = Unit

                override fun onAnimationEnd(animation: Animation) {
                    // set the bitmap in the imageView and fade/saturate in
                    imageView.setImageBitmap(bitmap)
                    imageView.alpha = 0f
                    matrix.setSaturation(0f)
                    val filter = ColorMatrixColorFilter(matrix)
                    imageView.colorFilter = filter

                    val alphaIn = ValueAnimator.ofFloat(0f, 1f)
                    alphaIn.interpolator = AccelerateDecelerateInterpolator()
                    alphaIn.duration = 200
                    alphaIn.addUpdateListener { animation1 -> imageView.alpha = animation1.animatedFraction }

                    val saturation = ValueAnimator.ofFloat(0f, 1f)
                    saturation.interpolator = AccelerateDecelerateInterpolator()
                    saturation.duration = 400
                    saturation.addUpdateListener { animation12 ->
                        matrix.setSaturation(animation12.animatedFraction)
                        val filter1 = ColorMatrixColorFilter(matrix)
                        imageView.colorFilter = filter1
                    }

                    val animatorSet = AnimatorSet()
                    animatorSet.playTogether(alphaIn, saturation)
                    animatorSet.start()
                }

                override fun onAnimationRepeat(animation: Animation) = Unit
            })
            imageView.startAnimation(alphaOut)
        } else {
            imageView.setImageBitmap(bitmap)
        }
    }

    override fun onPrepareLoad(placeHolderDrawable: Drawable) {
        imageView.setImageDrawable(placeHolderDrawable)
    }

    override fun equals(other: Any?): Boolean {
        return if (other is MaterialFadeTarget) {
            TextUtils.equals(other.imageUrl, imageUrl)
        } else super.equals(other)
    }

    override fun hashCode(): Int {
        var result = imageView.hashCode()
        result = 31 * result + imageUrl.hashCode()
        result = 31 * result + context.hashCode()
        return result
    }
}