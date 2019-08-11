package me.rahulsengupta.livepaper.collectiondetails.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_collection_wallpaper_layout.view.*
import me.rahulsengupta.livepaper.R
import me.rahulsengupta.livepaper.collectiondetails.models.CollectionWallpaperViewModel

class CollectionWallpaperPagedAdapter(
    private val listener: Listener
) : PagedListAdapter<CollectionWallpaperViewModel, CollectionWallpaperPagedAdapter.WallpaperViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WallpaperViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_collection_wallpaper_layout, parent, false)
        return WallpaperViewHolder(view)
    }

    override fun onBindViewHolder(holder: WallpaperViewHolder, position: Int) {
        getItem(position)?.run {
            Picasso.get().load(wallpaperUrl).into(holder.wallpaper)
            holder.root.setOnClickListener { listener.onWallpaperClicked(id) }
        }
    }

    override fun getItemId(position: Int) = position.toLong()
    override fun getItemViewType(position: Int) = position

    class WallpaperViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val root: MaterialCardView = view.item_wallpaper_root
        val wallpaper: AppCompatImageView = view.collection_wallpaper_image
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CollectionWallpaperViewModel>() {
            override fun areItemsTheSame(oldItem: CollectionWallpaperViewModel, newItem: CollectionWallpaperViewModel) =
                oldItem.wallpaperUrl == oldItem.wallpaperUrl

            override fun areContentsTheSame(
                oldItem: CollectionWallpaperViewModel,
                newItem: CollectionWallpaperViewModel
            ) =
                oldItem == newItem
        }
    }

    interface Listener {
        fun onWallpaperClicked(wallpaperId: String)
    }
}