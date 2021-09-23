package com.hardbobby.miniimdb.presentation.main.view.fragment

import androidx.fragment.app.viewModels
import com.hardbobby.domain.dto.modelview.MovieItemModelView
import com.hardbobby.domain.dto.modelview.SingleDataMovieModelView
import com.hardbobby.miniimdb.presentation.common.Constants
import com.hardbobby.miniimdb.presentation.main.viewmodel.PopularViewModel
import com.hardbobby.miniimdb.presentation.utils.observeEvent
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Bobby.Hardian on 22/09/2021.
 */
@AndroidEntryPoint
class PopularMovieFragment : MovieListFragment<PopularViewModel>() {

    override val viewModel: PopularViewModel by viewModels()

    override val pageType: Constants.PageType
        get() = Constants.PageType.PopularPage


    override fun addObserver() {
        viewModel.singleItemMovie().observeEvent(viewLifecycleOwner, ::handleSingleDataResult)
    }

    private fun handleSingleDataResult(result: SingleDataMovieModelView) {
        adapter?.notifyItemChanged(result.position, result.itemModelView)
    }

    override fun onFavoriteIconClicked(position: Int, itemModelView: MovieItemModelView) {
        viewModel.onFavoriteClicked(position = position, itemModelView = itemModelView)
    }

}