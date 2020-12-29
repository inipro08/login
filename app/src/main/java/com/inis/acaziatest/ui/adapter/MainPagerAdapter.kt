package com.inis.acaziatest.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.inis.acaziatest.ui.screen.fragment.home.HomeFragment
import com.inis.acaziatest.ui.screen.fragment.one.OneFragment
import com.inis.acaziatest.utils.Constants

class MainPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    companion object {
        private const val ITEM_COUNT = 3
    }

    override fun getItemCount() = ITEM_COUNT

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            Constants.MENU_POSITION_HOME -> HomeFragment()
            else -> OneFragment()
        }
    }
}