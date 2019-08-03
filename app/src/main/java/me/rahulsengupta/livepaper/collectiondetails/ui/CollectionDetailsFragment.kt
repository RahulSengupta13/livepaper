package me.rahulsengupta.livepaper.collectiondetails.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.transition.TransitionInflater
import kotlinx.android.synthetic.main.fragment_collection_detail.view.*
import me.rahulsengupta.livepaper.R
import me.rahulsengupta.livepaper.collectiondetails.CollectionDetailsAvm
import org.koin.android.viewmodel.ext.android.viewModel

class CollectionDetailsFragment : Fragment(), CollectionDetailsPresenter.Listener {

    private val avm: CollectionDetailsAvm by viewModel()
    private lateinit var presenterContainer: CollectionDetailsPresenter.Container

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_collection_detail, container, false)

        val toolbar = root.toolbar_collection_details
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        NavigationUI.setupActionBarWithNavController(activity as AppCompatActivity, findNavController())

        presenterContainer = CollectionDetailsPresenter.Container(root, this)

        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        sharedElementReturnTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)

        arguments?.let {
            val safeArgs = CollectionDetailsFragmentArgs.fromBundle(it)
            CollectionDetailsPresenter.setTransition(presenterContainer, safeArgs.transitionName)
            avm.setup(safeArgs.collectionId)
        }

        avm.presentCollectionDetails().observe(viewLifecycleOwner, Observer {
            CollectionDetailsPresenter.present(presenterContainer, it)
        })

        return root
    }
}