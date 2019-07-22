package me.rahulsengupta.livepaper.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import me.rahulsengupta.livepaper.R
import me.rahulsengupta.livepaper.home.HomeFragmentAvm
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(), HomeFragmentPresenter.Listener {

    private val avm: HomeFragmentAvm by viewModel()
    private lateinit var presenterContainer: HomeFragmentPresenter.Container

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        presenterContainer = HomeFragmentPresenter.Container(root, this, this)

        avm.setup()

        return root
    }
}