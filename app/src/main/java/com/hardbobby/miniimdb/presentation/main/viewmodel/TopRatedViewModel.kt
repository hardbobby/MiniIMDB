package com.hardbobby.miniimdb.presentation.main.viewmodel

import com.hardbobby.domain.usecase.TopRatedMovieUseCase
import com.hardbobby.miniimdb.presentation.common.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

/**
 * Created by Bobby.Hardian on 22/09/2021.
 */
@HiltViewModel
class TopRatedViewModel @Inject constructor(
    private val getTopRatedMovieUseCase: TopRatedMovieUseCase
) : MovieListViewModel() {

    override fun getData() {
        ioLaunch {
            getTopRatedMovieUseCase.execute(currentPage).collect { result ->
                _movieData.postValue(result)
                _loadingNextPage.postValue(Event(false))
            }
        }
    }
}