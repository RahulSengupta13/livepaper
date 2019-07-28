package me.rahulsengupta.livepaper.collections.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import me.rahulsengupta.livepaper.R
import me.rahulsengupta.livepaper.collections.CollectionsAvm
import me.rahulsengupta.livepaper.collections.models.FeaturedCollectionViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class CollectionsFragment : Fragment(), CollectionsPresenter.Listener {

    companion object {
        fun newInstance() = CollectionsFragment()
    }

    private val avm: CollectionsAvm by viewModel()
    private lateinit var presenterContainer: CollectionsPresenter.Container

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_collections, container, false)
        presenterContainer = CollectionsPresenter.Container(root, this)

        avm.collectionsPagedList().observe(viewLifecycleOwner, Observer {
            CollectionsPresenter.presentCollections(presenterContainer, it)
        })

        return root
    }


    override fun onAuthorClicked(viewModel: FeaturedCollectionViewModel) {
        val args = Bundle().apply { putSerializable("collectionDialogPayload", viewModel) }
        findNavController().navigate(R.id.action_homeFragment_to_collectionDialogFragment, args, null, null)
    }

    override fun onCollectionClicked(collectionId: Int?) {
        findNavController().navigate(R.id.action_homeFragment_to_collectionDetailsFragment)
    }

    override fun onSwipeToRefresh() = avm.onSwipeToRefresh()
}