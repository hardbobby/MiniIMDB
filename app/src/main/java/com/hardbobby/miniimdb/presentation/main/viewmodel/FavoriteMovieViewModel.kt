package com.hardbobby.miniimdb.presentation.main.viewmodel

import com.hardbobby.domain.usecase.FavouriteMovieUseCase
import com.hardbobby.miniimdb.presentation.common.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

/**
 * Created by Bobby.Hardian on 22/09/2021.
 */
@HiltViewModel
class FavoriteMovieViewModel @Inject constructor(
    private val getFavoriteUseCase: FavouriteMovieUseCase
) : MovieListViewModel() {
    override var isFromAPI: Boolean = false

    override fun getData() {
        ioLaunch {
            getFavoriteUseCase.execute().collect { result ->
                popularMovieList.clear()
                _movieData.postValue(result)
                _loadingNextPage.postValue(Event(false))
            }
        }
    }
}