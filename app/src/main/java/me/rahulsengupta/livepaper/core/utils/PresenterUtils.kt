package me.rahulsengupta.livepaper.core.utils

import android.graphics.Bitmap
import android.graphics.PorterDuff
import android.graphics.drawable.ColorDrawable
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