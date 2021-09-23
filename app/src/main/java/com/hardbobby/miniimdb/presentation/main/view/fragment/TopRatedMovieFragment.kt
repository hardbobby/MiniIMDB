package com.hardbobby.miniimdb.presentation.main.view.fragment

import androidx.fragment.app.viewModels
import com.hardbobby.domain.dto.modelview.MovieItemModelView
import com.hardbobby.miniimdb.presentation.common.Constants
import com.hardbobby.miniimdb.presentation.main.viewmodel.TopRatedViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Bobby.Hardian on 22/09/2021.
 */
@AndroidEntryPoint
class TopRatedMovieFragment :
    MovieListFragment<TopRatedViewModel>() {

    override val viewModel: TopRatedViewModel by viewModels()

    override val pageType: Constants.PageType
        get() = Constants.PageType.TopRatedPage

}