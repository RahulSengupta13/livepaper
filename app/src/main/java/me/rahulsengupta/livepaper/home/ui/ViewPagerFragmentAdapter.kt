package me.rahulsengupta.livepaper.home.ui

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import me.rahulsengupta.livepaper.collections.ui.CollectionsFragment
import me.rahulsengupta.livepaper.photos.ui.PhotosFragment

class ViewPagerFragmentAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    companion object {
        private const val TAB_COUNT = 2
    }

    override fun getItemCount() = TAB_COUNT

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> CollectionsFragment.newInstance()
            else -> PhotosFragment.newInstance()
        }
    }
}