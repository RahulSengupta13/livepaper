package me.rahulsengupta.livepaper.collectiondialog.ui

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import kotlinx.android.synthetic.main.collection_dialog_layout.view.*
import me.rahulsengupta.livepaper.collections.models.FeaturedCollectionViewModel
import me.rahulsengupta.livepaper.core.utils.setImage

class CollectionDialogPresenter {

    class Container(val root: View, val listener: Listener) {
        val authorImage = root.collection_author_image
        val collectionImage = root.collection_cover
        val dismissButton: AppCompatImageView = root.dismiss_collection_dialog
    }

    companion object {
        fun present(container: Container, viewModel: FeaturedCollectionViewModel) {
            container.authorImage.setImage(viewModel.authorImageUrl) { model ->
                model.muted?.let { container.authorImage.borderColor = it }
            }
            container.collectionImage.setImage(viewModel.coverUrl)
            container.dismissButton.setOnClickListener { container.listener.onDismissClicked() }
        }
    }

    interface Listener {
        fun onDismissClicked()
    }
}