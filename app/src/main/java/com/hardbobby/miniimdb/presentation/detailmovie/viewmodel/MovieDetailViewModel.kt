package com.hardbobby.miniimdb.presentation.detailmovie.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hardbobby.domain.common.Result
import com.hardbobby.domain.common.SimpleResult
import com.hardbobby.domain.dto.modelview.MovieDetailModelView
import com.hardbobby.domain.usecase.DetailMovieUseCase
import com.hardbobby.miniimdb.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

/**
 * Created by Bobby.Hardian on 22/09/2021.
 */
@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val detailMovieUseCase: DetailMovieUseCase
) : BaseViewModel() {

    private var _movieDetailData =
        MutableLiveData<SimpleResult<MovieDetailModelView>>(Result.State.Loading)
    val movieDetailData = _movieDetailData as LiveData<SimpleResult<MovieDetailModelView>>

    var movieTitle = ""

    fun getData(id: Int) {
        ioLaunch {
            detailMovieUseCase.execute(id).collect {
                _movieDetailData.postValue(it)
            }
        }
    }
}