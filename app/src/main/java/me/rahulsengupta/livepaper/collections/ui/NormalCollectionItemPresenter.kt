package me.rahulsengupta.livepaper.collections.ui

import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.mikhaellopez.circularimageview.CircularImageView
import kotlinx.android.synthetic.main.item_collection_layout.view.*
import me.rahulsengupta.livepaper.R
import me.rahulsengupta.livepaper.collections.models.NormalCollectionViewModel
import me.rahulsengupta.livepaper.core.utils.setImage
import me.rahulsengupta.livepaper.core.utils.setVectorColor

class NormalCollectionItemPresenter {

    class Container(rootView: View, val listener: Listener) : RecyclerView.ViewHolder(rootView) {
        val root: MaterialCardView = rootView.collection_root
        val collectionImage: AppCompatImageView = rootView.collection_image
        val info: AppCompatImageView = rootView.collection_info
        val titleAndAuthor: TextView = rootView.collection_title_author
        val photoCount: TextView = rootView.collection_photos_count
        val authorImage: CircularImageView = rootView.collection_author_image
    }

    companion object {
        fun present(container: Container, viewModel: NormalCollectionViewModel) {
            container.collectionImage.setImage(viewModel.coverUrl) {
                container.info.setVectorColor(it.lightVibrant)
            }
            container.titleAndAuthor.text = container.titleAndAuthor.context.getString(
                R.string.collections_title_and_author_format,
                viewModel.collectionName,
                viewModel.authorName
            )
            container.photoCount.visibility = View.GONE
            container.authorImage.setImage(viewModel.authorImageUrl) { model ->
                model.muted?.let { container.authorImage.borderColor = it }
            }
            container.root.setOnClickListener { Unit }
        }
    }

    interface Listener
}