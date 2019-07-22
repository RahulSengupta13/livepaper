package me.rahulsengupta.livepaper.collections.ui

import android.view.View
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.android.synthetic.main.fragment_collections.view.*
import me.rahulsengupta.livepaper.collections.models.CollectionViewModel

class CollectionsPresenter {

    class Container(private val root: View, val listener: Listener) {
        val recyclerView: RecyclerView = root.collections_recycler_view
        val swipeRefresh: SwipeRefreshLayout = root.swipe_refresh
        val collectionsAdapter: CollectionsPagedAdapter

        init {
            recyclerView.apply {
                layoutManager = GridLayoutManager(root.context, GRID_SPAN, RecyclerView.VERTICAL, false)
                setHasFixedSize(true)
            }

            collectionsAdapter = CollectionsPagedAdapter(listener)
            recyclerView.adapter = collectionsAdapter
        }
    }

    companion object {
        private const val GRID_SPAN = 2

        fun presentCollections(container: Container, collections: PagedList<CollectionViewModel>) {
            container.collectionsAdapter.submitList(collections)
        }
    }

    interface Listener : CollectionsPagedAdapter.Listener
}