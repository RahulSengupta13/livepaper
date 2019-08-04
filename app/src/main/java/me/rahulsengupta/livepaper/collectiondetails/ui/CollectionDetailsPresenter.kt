package me.rahulsengupta.livepaper.collectiondetails.ui

import android.view.View
import android.widget.TextView
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mikhaellopez.circularimageview.CircularImageView
import kotlinx.android.synthetic.main.custom_chip_layout.view.*
import kotlinx.android.synthetic.main.fragment_collection_detail.view.*
import me.rahulsengupta.livepaper.collectiondetails.models.CollectionDetailsViewModel
import me.rahulsengupta.livepaper.collectiondetails.models.CollectionWallpaperViewModel
import me.rahulsengupta.livepaper.core.utils.setImage
import me.rahulsengupta.livepaper.core.utils.setTextAndVisibility
import me.rahulsengupta.livepaper.core.utils.setVisibility

class CollectionDetailsPresenter {

    class Container(val root: View, val listener: Listener) {
        private val recyclerView: RecyclerView = root.collection_details_recycler_view
        val wallpaperAdapter: CollectionWallpaperPagedAdapter
        val coverPhoto: CircularImageView = root.collection_detail_coverPhoto
        val collectionName: TextView = root.collection_detail_name
        val collectionDescription: TextView = root.collection_detail_description
        val collectionAuthorImage: CircularImageView = root.chip_image
        val collectionAuthorName: TextView = root.chip_text
        val collectionAuthorInstagram: TextView = root.collection_detail_author_instagram
        val collectionAuthorTwitter: TextView = root.collection_detail_author_twitter

        init {
            recyclerView.apply {
                layoutManager = GridLayoutManager(root.context, GRID_SPAN, RecyclerView.VERTICAL, false)
            }

            wallpaperAdapter = CollectionWallpaperPagedAdapter(listener)
            recyclerView.adapter = wallpaperAdapter
        }
    }

    companion object {
        private const val GRID_SPAN = 2

        fun present(container: Container, viewModel: CollectionDetailsViewModel) {
            viewModel.collectionCoverPhoto?.let {
                container.coverPhoto.setImage(it) { model ->
                    model.lightVibrant?.let { container.coverPhoto.borderColor = it }
                }
            }
            container.collectionName.text = viewModel.collectionName
            container.collectionDescription.setTextAndVisibility(viewModel.collectionDescription)
            container.collectionAuthorName.text = viewModel.collectionAuthor
            viewModel.collectionAuthorPhoto?.let { container.collectionAuthorImage.setImage(it) }
            container.collectionAuthorInstagram.setVisibility(viewModel.instagramUsername)
            container.collectionAuthorTwitter.setVisibility(viewModel.twitterUsername)

            container.collectionAuthorInstagram.setOnClickListener { container.listener.onInstagramClicked(viewModel.instagramUsername) }
            container.collectionAuthorTwitter.setOnClickListener { container.listener.onTwitterClicked(viewModel.twitterUsername) }
        }

        fun setTransition(container: Container, transitionName: String) {
            container.coverPhoto.transitionName = transitionName
        }

        fun presentWallpapers(container: Container, wallpapers: PagedList<CollectionWallpaperViewModel>) {
            container.wallpaperAdapter.submitList(wallpapers)
        }
    }

    interface Listener : CollectionWallpaperPagedAdapter.Listener {
        fun onInstagramClicked(instagramUsername: String?)
        fun onTwitterClicked(twitterUsername: String?)
    }
}
