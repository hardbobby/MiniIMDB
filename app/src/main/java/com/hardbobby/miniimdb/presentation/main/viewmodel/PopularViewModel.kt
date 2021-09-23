package com.hardbobby.miniimdb.presentation.main.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import com.hardbobby.domain.dto.modelview.MovieItemModelView
import com.hardbobby.domain.dto.modelview.SingleDataMovieModelView
import com.hardbobby.domain.usecase.DeleteFavoriteMovieUseCase
import com.hardbobby.domain.usecase.DetailMovieUseCase
import com.hardbobby.domain.usecase.PopularMovieUseCase
import com.hardbobby.domain.usecase.SaveFavoriteMovieUseCase
import com.hardbobby.miniimdb.presentation.common.Event
import com.hardbobby.miniimdb.presentation.utils.MutableLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

/**
 * Created by Bobby.Hardian on 22/09/2021.
 */
@HiltViewModel
class PopularViewModel @Inject constructor(
    private val getPopularMovieUseCase: PopularMovieUseCase,
    private val saveFavoriteMovieUseCase: SaveFavoriteMovieUseCase,
    private val deleteFavoriteMovieUseCase: DeleteFavoriteMovieUseCase,
    private val detailMovieUseCase: DetailMovieUseCase
) : MovieListViewModel() {


    private val _singleItemMovie = MutableLiveEvent<SingleDataMovieModelView>()
    fun singleItemMovie() = _singleItemMovie as LiveData<Event<SingleDataMovieModelView>>

    override fun getData() {
        ioLaunch {
            getPopularMovieUseCase.execute(currentPage).collect { result ->
                _movieData.postValue(result)
                _loadingNextPage.postValue(Event(false))
            }
        }
    }

    fun onFavoriteClicked(position: Int, itemModelView: MovieItemModelView) {
        ioLaunch {
            if (itemModelView.isFavourite) {
                itemModelView.isFavourite = false
                deleteFavoriteMovieUseCase.execute(itemModelView.idMovie)
            } else {
                saveFavoriteMovieUseCase.execute(itemModelView)
                itemModelView.isFavourite = true
            }
            detailMovieUseCase.execute(itemModelView.idMovie).collect {
                Log.wtf("TEST","POINT")
            }
            _singleItemMovie.postValue(Event(SingleDataMovieModelView(position, itemModelView)))
        }
    }
}