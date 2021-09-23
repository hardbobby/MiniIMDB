package com.hardbobby.miniimdb.presentation.main.view.fragment

import androidx.fragment.app.viewModels
import com.hardbobby.miniimdb.presentation.common.Constants
import com.hardbobby.miniimdb.presentation.main.viewmodel.FavoriteMovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import setGone
import setVisible

/**
 * Created by Bobby.Hardian on 22/09/2021.
 */
@AndroidEntryPoint
class FavoriteMovieFragment :
    MovieListFragment<FavoriteMovieViewModel>() {

    override val viewModel: FavoriteMovieViewModel by viewModels()

    override val pageType: Constants.PageType
        get() = Constants.PageType.FavoritePage

    override fun onResume() {
        super.onResume()
        viewModel.getData()
    }

}