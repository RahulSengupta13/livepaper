package me.rahulsengupta.livepaper.collectiondetails.ui

import android.view.View
import android.widget.TextView
import com.mikhaellopez.circularimageview.CircularImageView
import kotlinx.android.synthetic.main.custom_chip_layout.view.*
import kotlinx.android.synthetic.main.fragment_collection_detail.view.*
import me.rahulsengupta.livepaper.collectiondetails.models.CollectionDetailsViewModel
import me.rahulsengupta.livepaper.core.utils.setImage
import me.rahulsengupta.livepaper.core.utils.setTextAndVisibility

class CollectionDetailsPresenter {

    class Container(val root: View, val listener: Listener) {
        val coverPhoto: CircularImageView = root.collection_detail_coverPhoto
        val collectionName: TextView = root.collection_detail_name
        val collectionDescription: TextView = root.collection_detail_description
        val collectionAuthorImage: CircularImageView = root.chip_image
        val collectionAuthorName: TextView = root.chip_text
    }

    companion object {
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
        }

        fun setTransition(container: Container, transitionName: String) {
            container.coverPhoto.transitionName = transitionName
        }
    }

    interface Listener
}