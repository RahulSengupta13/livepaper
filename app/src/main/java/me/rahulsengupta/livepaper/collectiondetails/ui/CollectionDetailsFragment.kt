package me.rahulsengupta.livepaper.collectiondetails.ui

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.collection_details_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

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

        avm.collectionWallpaperPagedList().observe(viewLifecycleOwner, Observer {
            CollectionDetailsPresenter.presentWallpapers(presenterContainer, it)
        })

        return root
    }

    override fun onInstagramClicked(instagramUsername: String?) {
        try {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("http://instagram.com/$instagramUsername")
                setPackage("com.instagram.android")
            }
            startActivity(intent)
        } catch (anfe: ActivityNotFoundException) {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("http://instagram.com/_u/$instagramUsername")))
        }
    }

    override fun onTwitterClicked(twitterUsername: String?) {
        try {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("https://twitter.com/$twitterUsername")
                setPackage("com.twitter.android")
            }
            startActivity(intent)
        } catch (anfe: ActivityNotFoundException) {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/$twitterUsername")))
        }
    }
}