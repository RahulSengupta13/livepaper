package me.rahulsengupta.livepaper.collections.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_collection_layout.view.*
import me.rahulsengupta.livepaper.R
import me.rahulsengupta.livepaper.collections.models.CollectionViewModel

class CollectionsPagedAdapter(
    val listener: Listener
) : PagedListAdapter<CollectionViewModel, CollectionsPagedAdapter.CollectionViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_collection_layout, parent, false)
        return CollectionViewHolder(view)
    }

    override fun onBindViewHolder(holder: CollectionViewHolder, position: Int) {
        getItem(position)?.run {
            Picasso.get().load(coverUrl).into(holder.collectionImage)
            holder.root.setOnClickListener { Unit }
        }
    }

    override fun getItemId(position: Int) = position.toLong()
    override fun getItemViewType(position: Int) = position

    class CollectionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val root: MaterialCardView = view.collection_root
        val collectionImage: AppCompatImageView = view.collection_image
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CollectionViewModel>() {
            override fun areItemsTheSame(oldItem: CollectionViewModel, newItem: CollectionViewModel) =
                oldItem.collectionId == oldItem.collectionId
            override fun areContentsTheSame(oldItem: CollectionViewModel, newItem: CollectionViewModel) =
                oldItem == newItem
        }
    }

    interface Listener
}