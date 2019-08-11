package me.rahulsengupta.livepaper.wallpaper.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import me.rahulsengupta.livepaper.R
import me.rahulsengupta.livepaper.wallpaper.WallpaperAvm
import org.koin.android.viewmodel.ext.android.viewModel

class WallpaperFragment : Fragment(), WallpaperPresenter.Listener {

    private lateinit var presenterContainer: WallpaperPresenter.Container
    private val avm: WallpaperAvm by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_wallpaper, container, false)
        presenterContainer = WallpaperPresenter.Container(root, this)

        avm.present().observe(viewLifecycleOwner, Observer { WallpaperPresenter.present(presenterContainer, it) })

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val safeArgs = WallpaperFragmentArgs.fromBundle(it)
            avm.setup(safeArgs.wallpaperId)
        } ?: run {
            fragmentManager?.popBackStack()
        }
    }
}