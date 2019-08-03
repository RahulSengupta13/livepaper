package me.rahulsengupta.livepaper.core.utils

import android.graphics.Bitmap
import android.graphics.PorterDuff
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.ViewTreeObserver
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.palette.graphics.Palette
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