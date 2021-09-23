package com.hardbobby.miniimdb.presentation.main.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.hardbobby.domain.common.SimpleResult
import com.hardbobby.miniimdb.databinding.ActivityMainBinding
import com.hardbobby.miniimdb.presentation.common.Constants
import com.hardbobby.miniimdb.presentation.main.view.viewpager.ViewPagerAdapter
import com.hardbobby.miniimdb.presentation.main.viewmodel.MainViewModel
import com.hardbobby.miniimdb.presentation.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)
    private val viewModel: MainViewModel by viewModels()

    private lateinit var pagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initView()
        initListener()
    }

    private fun initView() {
        pagerAdapter = ViewPagerAdapter(this)
        with(binding) {
            viewPager.adapter = pagerAdapter
            TabLayoutMediator(tabLayout, viewPager) { tab, pos ->
                tab.text = pagerAdapter.title[pos]
            }.attach()
            viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        }
    }

    private fun initListener() {
        viewModel.counterMovieFavorite().observe(this, ::setCounter)
    }

    private fun setCounter(result: SimpleResult<Int>) {
        result.handleResult(
            successDataBlock = {
                val tabLayoutFavorite = binding.tabLayout.getTabAt(Constants.tabMenu.size - 1)
                tabLayoutFavorite?.text = "${Constants.tabMenu[2]} (${it})"
            },
            successNoDataBlock = {
                val tabLayoutFavorite = binding.tabLayout.getTabAt(Constants.tabMenu.size - 1)
                tabLayoutFavorite?.text = Constants.tabMenu[2]
            }
        )
    }
}

