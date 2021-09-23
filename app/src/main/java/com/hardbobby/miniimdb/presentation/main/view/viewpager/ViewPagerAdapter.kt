package com.hardbobby.miniimdb.presentation.main.view.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.hardbobby.miniimdb.presentation.common.Constants
import com.hardbobby.miniimdb.presentation.main.view.fragment.FavoriteMovieFragment
import com.hardbobby.miniimdb.presentation.main.view.fragment.PopularMovieFragment
import com.hardbobby.miniimdb.presentation.main.view.fragment.TopRatedMovieFragment

/**
 * Created by Bobby.Hardian on 22/09/2021.
 */
class ViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    val title = Constants.tabMenu

    private val fragments = listOf(
        PopularMovieFragment(), TopRatedMovieFragment(),
        FavoriteMovieFragment()
    )

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

}

