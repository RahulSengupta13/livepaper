package me.rahulsengupta.livepaper.collections.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.transition.TransitionInflater
import kotlinx.android.synthetic.main.fragment_collections.view.*
import me.rahulsengupta.livepaper.R
import me.rahulsengupta.livepaper.collections.CollectionsAvm
import me.rahulsengupta.livepaper.collections.models.FeaturedCollectionViewModel
import me.rahulsengupta.livepaper.core.utils.afterMeasure
import org.koin.android.viewmodel.ext.android.viewModel

class CollectionsFragment : Fragment(), CollectionsPresenter.Listener {

    companion object {
        fun newInstance() = CollectionsFragment()
    }

    private val avm: CollectionsAvm by viewModel()
    private lateinit var presenterContainer: CollectionsPresenter.Container

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_collections, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenterContainer = CollectionsPresenter.Container(view, this)
        avm.collectionsPagedList().observe(viewLifecycleOwner, Observer {
            CollectionsPresenter.presentCollections(presenterContainer, it)
        })
    }


    override fun onAuthorClicked(viewModel: FeaturedCollectionViewModel) {
        val args = Bundle().apply { putSerializable("collectionDialogPayload", viewModel) }
        findNavController().navigate(R.id.action_homeFragment_to_collectionDialogFragment, args, null, null)
    }

    override fun onCollectionClicked(
        collectionId: Int?,
        collectionImage: AppCompatImageView,
        transitionName: String
    ) {
        if (collectionId != null) {
            val args = Bundle().apply {
                putInt("collectionId", collectionId)
                putString("transitionName", transitionName)
            }
            val extras = FragmentNavigatorExtras(
                collectionImage to transitionName
            )
            findNavController().navigate(R.id.action_homeFragment_to_collectionDetailsFragment, args, null, extras)
        }
    }

    override fun onSwipeToRefresh() = avm.onSwipeToRefresh()
}