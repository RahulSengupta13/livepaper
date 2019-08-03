package me.rahulsengupta.livepaper.collectiondetails.ui

import android.view.View
import android.widget.TextView
import com.mikhaellopez.circularimageview.CircularImageView
import kotlinx.android.synthetic.main.fragment_collection_detail.view.*
import me.rahulsengupta.livepaper.collectiondetails.models.CollectionDetailsViewModel
import me.rahulsengupta.livepaper.core.utils.setImage

class CollectionDetailsPresenter {

    class Container(val root: View, val listener: Listener) {
        val coverPhoto: CircularImageView = root.collection_detail_coverPhoto
        val author: TextView = root.collection_detail_author
    }

    companion object {
        fun present(container: Container, viewModel: CollectionDetailsViewModel) {
            viewModel.collectionCoverPhoto?.let {
                container.coverPhoto.setImage(it)
            }
            container.author.text = viewModel.collectionAuthor
        }

        fun setTransition(container: Container, transitionName: String) {
            container.coverPhoto.transitionName = transitionName
        }
    }

    interface Listener
}