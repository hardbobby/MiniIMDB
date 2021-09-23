package com.hardbobby.miniimdb.presentation.detailmovie.view

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import com.hardbobby.domain.common.Result
import com.hardbobby.domain.dto.modelview.MovieDetailModelView
import com.hardbobby.miniimdb.R
import com.hardbobby.miniimdb.databinding.ActivityDetailMovieBinding
import com.hardbobby.miniimdb.presentation.common.Constants
import com.hardbobby.miniimdb.presentation.detailmovie.view.adapter.ItemCastAdapter
import com.hardbobby.miniimdb.presentation.detailmovie.viewmodel.MovieDetailViewModel
import com.hardbobby.miniimdb.presentation.utils.GlideUtils
import com.hardbobby.miniimdb.presentation.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import setGone
import setVisible

/**
 * Created by Bobby.Hardian on 22/09/2021.
 */
@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityDetailMovieBinding::inflate)
    private val viewModel by viewModels<MovieDetailViewModel>()

    private var adapter: ItemCastAdapter = ItemCastAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel.getData(intent.getIntExtra(Constants.EXTRA_ID_MOVIE, 0))
        initListener()
        setupView()
    }

    private fun setupView() {
        setupToolbar()
        adapter = ItemCastAdapter()
        binding.movieDetailsInfo.recyclerViewListCast.adapter = adapter
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        handleCollapsedToolbarTitle()
    }

    private fun initListener() {
        viewModel.movieDetailData.observe(this, {
            it.handleResult(successDataBlock = {
                onSuccessData(it)
            }, successNoDataBlock = {
                onLoadingSuccessNoData()
                binding.textViewErrorMessage.text =
                    getString(R.string.label_no_available_information)
            }, failureBlock = {
                onLoadingSuccessNoData()
                binding.textViewErrorMessage.text = it.errorMessage
            }) { state ->
                when (state) {
                    Result.State.Loading -> {
                        onLoadingData()
                    }
                }
            }
        })
    }

    private fun onLoadingData() {
        with(binding) {
            progressLoading.setVisible()
            appBarLayout.setGone()
            collapsingToolbar.setGone()
            movieDetails.setGone()
            textViewErrorMessage.setGone()
        }
    }

    private fun onLoadingSuccessData() {
        with(binding) {
            progressLoading.setGone()
            appBarLayout.setVisible()
            collapsingToolbar.setVisible()
            movieDetails.setVisible()
            textViewErrorMessage.setGone()
        }
    }

    private fun onLoadingSuccessNoData() {
        with(binding) {
            progressLoading.setGone()
            appBarLayout.setGone()
            collapsingToolbar.setGone()
            movieDetails.setGone()
            textViewErrorMessage.setVisible()
        }
    }


    private fun onSuccessData(movieDetailModelView: MovieDetailModelView) {
        onLoadingSuccessData()
        with(binding) {
            progressLoading.hide()
            GlideUtils.getGlide(movieDetailModelView.backdropUrl, this@MovieDetailActivity)
                .into(imageMovieBackdrop)

            with(movieDetailsInfo) {
                textViewTitle.text = movieDetailModelView.nameMovie
                viewModel.movieTitle = movieDetailModelView.nameMovie
                textViewLang.text = movieDetailModelView.originalLanguage
                textViewRating.text = movieDetailModelView.voteAverage.toString()
                textViewVotes.text = movieDetailModelView.voteCount.toString()
                textViewDate.text = movieDetailModelView.releaseDate
                textOverview.text = movieDetailModelView.overviewMovie
                adapter.submitList(movieDetailModelView.listCast)
                GlideUtils.getGlide(movieDetailModelView.posterUrl, this@MovieDetailActivity)
                    .into(imagePoster)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun handleCollapsedToolbarTitle() {
        binding.appBarLayout.addOnOffsetChangedListener(object : OnOffsetChangedListener {
            var isShow = true
            var scrollRange = -1
            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.totalScrollRange
                }
                if (scrollRange + verticalOffset == 0) {
                    binding.collapsingToolbar.title = viewModel.movieTitle
                    isShow = true
                } else if (isShow) {
                    binding.collapsingToolbar.title = " "
                    isShow = false
                }
            }
        })
    }
}

