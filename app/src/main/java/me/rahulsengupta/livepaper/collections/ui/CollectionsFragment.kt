package me.rahulsengupta.livepaper.collections.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import me.rahulsengupta.livepaper.R

class CollectionsFragment : Fragment() {

    companion object {
        fun newInstance() = CollectionsFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_collections, container, false)
    }
}