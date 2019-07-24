package me.rahulsengupta.livepaper.collections.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import me.rahulsengupta.livepaper.R
import me.rahulsengupta.livepaper.collections.models.CollectionViewModel
import me.rahulsengupta.livepaper.collections.models.FeaturedCollectionViewModel
import me.rahulsengupta.livepaper.collections.models.NormalCollectionViewModel
import me.rahulsengupta.livepaper.collections.ui.CollectionsPagedAdapter.ViewType.*

class CollectionsPagedAdapter(
    val listener: Listener
) : PagedListAdapter<CollectionViewModel, RecyclerView.ViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewTypeEnum = values()[viewType]
        val root = LayoutInflater.from(parent.context).inflate(viewTypeEnum.layoutId, parent, false)
        return when (viewTypeEnum) {
            ITEM_FEATURED -> FeaturedCollectionItemPresenter.Container(root, listener)
            ITEM_NORMAL -> NormalCollectionItemPresenter.Container(root, listener)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.run {
            when (this) {
                is FeaturedCollectionViewModel -> FeaturedCollectionItemPresenter.present(
                    holder as FeaturedCollectionItemPresenter.Container,
                    this
                )
                is NormalCollectionViewModel -> NormalCollectionItemPresenter.present(
                    holder as NormalCollectionItemPresenter.Container,
                    this
                )
            }
        }
    }

    override fun getItemId(position: Int) = position.toLong()
    override fun getItemViewType(position: Int) = getItem(position)?.viewType()?.ordinal ?: 0

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CollectionViewModel>() {
            override fun areItemsTheSame(oldItem: CollectionViewModel, newItem: CollectionViewModel) =
                oldItem.uniqueId() == oldItem.uniqueId()

            override fun areContentsTheSame(oldItem: CollectionViewModel, newItem: CollectionViewModel) =
                oldItem.equals(newItem)
        }
    }

    enum class ViewType(@LayoutRes val layoutId: Int) {
        ITEM_NORMAL(R.layout.item_collection_layout),
        ITEM_FEATURED(R.layout.item_collection_layout)
    }

    interface Listener :
        FeaturedCollectionItemPresenter.Listener,
        NormalCollectionItemPresenter.Listener
}