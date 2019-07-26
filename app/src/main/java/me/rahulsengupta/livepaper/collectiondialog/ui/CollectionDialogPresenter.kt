package me.rahulsengupta.livepaper.collectiondialog.ui

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import com.mikhaellopez.circularimageview.CircularImageView
import kotlinx.android.synthetic.main.collection_dialog_layout.view.*
import me.rahulsengupta.livepaper.R
import me.rahulsengupta.livepaper.collections.models.FeaturedCollectionViewModel
import me.rahulsengupta.livepaper.core.utils.setImage

class CollectionDialogPresenter {

    class Container(val root: View, val listener: Listener) {
        val authorImage: CircularImageView = root.collection_author_image
        val collectionImage: ImageView = root.collection_cover
        val collectionAuthor: TextView = root.collection_author
        val collectionName: TextView = root.collection_name
        val collectionDescription: TextView = root.collection_description
        val collectionDescriptionIcon: TextView = root.collection_description_icon
        val collectionPhotoCount: TextView = root.collection_photo_count
        val dismissButton: AppCompatImageView = root.dismiss_collection_dialog
    }

    companion object {
        fun present(container: Container, viewModel: FeaturedCollectionViewModel) {
            container.authorImage.setImage(viewModel.authorImageUrl) { model ->
                model.muted?.let { container.authorImage.borderColor = it }
            }
            container.collectionImage.setImage(viewModel.coverUrl)
            container.collectionAuthor.text = viewModel.authorName
            container.collectionName.text = viewModel.collectionName
            viewModel.collectionDescription?.let {
                container.collectionDescription.text = container.root.context.getString(R.string.collection_dialog_collection_description, it)
            } ?: run {
                container.collectionDescription.visibility = View.GONE
                container.collectionDescriptionIcon.visibility = View.GONE
            }
            container.collectionPhotoCount.text = container.root.context.resources.getQuantityString(
                R.plurals.photos,
                viewModel.photoCount ?: 0,
                viewModel.photoCount
            )
            container.dismissButton.setOnClickListener { container.listener.onDismissClicked() }
        }
    }

    interface Listener {
        fun onDismissClicked()
    }
}