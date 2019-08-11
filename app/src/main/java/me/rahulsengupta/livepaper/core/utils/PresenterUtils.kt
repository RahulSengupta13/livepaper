package me.rahulsengupta.livepaper.core.utils

import android.graphics.Bitmap
import android.graphics.PorterDuff
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewTreeObserver
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.davemorrissey.labs.subscaleview.ImageSource
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView
import com.squareup.picasso.Picasso
import me.rahulsengupta.livepaper.R

fun ImageView.setImage(
    imageUrl: String,
    block: ((PaletteColorModel) -> Unit) = {}
) {
    Picasso
        .get()
        .load(imageUrl)
        .placeholder(ColorDrawable(ContextCompat.getColor(context, R.color.colorPrimaryDark)))
        .into(MaterialFadeTarget(this, imageUrl, context, block))
}

fun Bitmap.createPalette(): Palette = Palette.from(this).generate()

fun ImageView.setVectorColor(color: Int?) {
    color?.let {
        setColorFilter(it, PorterDuff.Mode.SRC_IN)
    }
}

inline fun <T : View> T.afterMeasure(crossinline f: T.() -> Unit) {
    viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            if (measuredWidth > 0 && measuredHeight > 0) {
                viewTreeObserver.removeOnGlobalLayoutListener(this)
                f()
            }
        }
    })
}

fun TextView.setTextAndVisibility(string: String?) {
    string?.let {
        visibility = View.VISIBLE
        text = string
    } ?: run {
        visibility = View.GONE
    }
}

fun TextView.setVisibility(string: String?) {
    string?.let {
        visibility = View.VISIBLE
    } ?: run {
        visibility = View.GONE
    }
}

fun SubsamplingScaleImageView.setImageAndAnimate(imageUrl: String) {
    Glide
        .with(context)
        .asBitmap()
        .load(imageUrl)
        .into(object: CustomTarget<Bitmap>() {
            override fun onLoadCleared(placeholder: Drawable?) = Unit
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                setImage(ImageSource.bitmap(resource))
                animateScale(1.25f)?.start()
            }
        })
}