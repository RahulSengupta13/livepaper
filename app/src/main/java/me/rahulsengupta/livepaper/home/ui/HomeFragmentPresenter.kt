package me.rahulsengupta.livepaper.home.ui

import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_home.view.*
import me.rahulsengupta.livepaper.R

class HomeFragmentPresenter {

    class Container(root: View, fragment: Fragment, val listener: Listener) {
        private val tabLayout: TabLayout = root.tab_layout
        private val viewPager: ViewPager2 = root.viewpager
        private val viewPagerAdapter: FragmentStateAdapter

        init {
            viewPagerAdapter = ViewPagerFragmentAdapter(fragment)
            viewPager.adapter = viewPagerAdapter
            TabLayoutMediator(
                tabLayout,
                viewPager,
                TabLayoutMediator.OnConfigureTabCallback { tab, position ->
                    tab.text = when(position) {
                        0 -> root.context.getString(R.string.tab_collections)
                        else -> root.context.getString(R.string.tab_photos)
                    }
                }
            ).attach()
        }
    }

    interface Listener
}