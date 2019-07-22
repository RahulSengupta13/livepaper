package me.rahulsengupta.livepaper.photos.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import me.rahulsengupta.livepaper.R

class PhotosFragment : Fragment() {

    companion object {
        fun newInstance() = PhotosFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_photos, container, false)
    }
}