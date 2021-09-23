package com.hardbobby.miniimdb.presentation.main.view.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.hardbobby.domain.common.Result
import com.hardbobby.domain.common.SimpleResult
import com.hardbobby.domain.dto.modelview.MovieItemModelView
import com.hardbobby.miniimdb.R
import com.hardbobby.miniimdb.databinding.FragmentPopularListBinding
import com.hardbobby.miniimdb.presentation.base.viewBinding
import com.hardbobby.miniimdb.presentation.common.Constants
import com.hardbobby.miniimdb.presentation.common.Constants.EXTRA_ID_MOVIE
import com.hardbobby.miniimdb.presentation.common.Event
import com.hardbobby.miniimdb.presentation.detailmovie.view.MovieDetailActivity
import com.hardbobby.miniimdb.presentation.main.view.adapter.ItemMovieAdapter
import com.hardbobby.miniimdb.presentation.main.viewmodel.MovieListViewModel
import com.hardbobby.miniimdb.presentation.utils.launchActivity
import com.hardbobby.miniimdb.presentation.utils.observeEvent
import setGone
import setVisible
import showErrorSnackbar
import showIf
import showSlidingUpIf

/**
 * Created by Bobby.Hardian on 22/09/2021.
 */
abstract class MovieListFragment<ViewModel : MovieListViewModel> :
    Fragment(R.layout.fragment_popular_list) {
    private val binding by viewBinding(FragmentPopularListBinding::bind)
    protected abstract val viewModel: ViewModel
    protected abstract val pageType: Constants.PageType
    protected var adapter: ItemMovieAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupObserver()
        setupListener()
        viewModel.getData()
    }

    private fun setupView() {
        with(binding) {
            adapter = ItemMovieAdapter(
                pageType = pageType,
                onLoadNextPage = {
                    viewModel.fetchNextPage()
                },
                onMovieClicked = {
                    if (pageType != Constants.PageType.FavoritePage) {
                        viewModel.showMovieDetail.postValue(Event(it.idMovie))
                    }
                },
                onFavoriteIconClicked = { position, item ->
                    onFavoriteIconClicked(position, item)
                }
            )
            recyclerViewMovieList.adapter = adapter
        }
    }

    private fun setupListener() {
        with(binding) {
            if (pageType != Constants.PageType.FavoritePage) {
                pullToRefresh.setOnRefreshListener {
                    textViewErrorMessage.setGone()
                    recyclerViewMovieList.setGone()
                    startLoading()
                    adapter?.submitList(null)
                    viewModel.refreshData()
                }
            } else {
                pullToRefresh.isEnabled = false
            }
        }
    }

    private fun setupObserver() {
        viewModel.movieData.observe(viewLifecycleOwner, ::handleMovieListResult)
        viewModel.loading().observeEvent(viewLifecycleOwner, ::handlePageLoading)

        viewModel.showMovieDetail.observeEvent(viewLifecycleOwner) { movieId ->
            context?.launchActivity<MovieDetailActivity> {
                putExtra(EXTRA_ID_MOVIE, movieId)
            }
        }
        addObserver()
    }

    open fun addObserver() {
        //Do nothing
    }

    open fun onFavoriteIconClicked(position: Int, itemModelView: MovieItemModelView) {
        //Do nothing
    }

    private fun handleMovieListResult(result: SimpleResult<List<MovieItemModelView>>) {
        result.handleResult(
            successDataBlock = { data ->
                onSuccessResult(data)
            }, successNoDataBlock = {
                stopLoading()
                onFinishLoadData()
            }, failureBlock = {
                stopLoading()
                showErrorSnackbar(it.errorMessage)
                onFinishLoadData()
            }
        ) { state ->
            when (state) {
                Result.State.Loading -> {
                    onLoadingData()
                }
            }
        }
    }

    private fun onSuccessResult(data: List<MovieItemModelView>) {
        stopLoading()
        viewModel.addListData(data)
        adapter?.submitList(viewModel.popularMovieList.toMutableList())
        onFinishLoadData()
    }

    private fun handlePageLoading(show: Boolean) {
        binding.linearLayoutProgressBottom.showSlidingUpIf(show)
    }

    private fun onLoadingData() {
        startLoading()
        with(binding) {
            textViewErrorMessage.setGone()
            recyclerViewMovieList.setGone()
        }
    }

    private fun stopLoading() {
        binding.progressLoading.setGone()
    }

    private fun startLoading() {
        binding.progressLoading.setVisible()
    }

    private fun showErrorSnackbar(message: String) {
        (activity?.findViewById(android.R.id.content) as? View)?.let {
            view?.showErrorSnackbar(message)
        }
    }

    private fun onFinishLoadData() {
        with(binding) {
            handlePageLoading(false)
            pullToRefresh.isRefreshing = false
            textViewErrorMessage.showIf { viewModel.popularMovieList.isNullOrEmpty() }
            recyclerViewMovieList.showIf { !viewModel.popularMovieList.isNullOrEmpty() }
        }
    }

}