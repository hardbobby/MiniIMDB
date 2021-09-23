package com.hardbobby.miniimdb.presentation.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hardbobby.domain.common.SimpleResult
import com.hardbobby.domain.usecase.FavoriteMovieCounterUseCase
import com.hardbobby.miniimdb.presentation.base.BaseViewModel
import com.hardbobby.miniimdb.presentation.common.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

/**
 * Created by Bobby.Hardian on 21/09/2021.
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val favoriteMovieCounterUseCase: FavoriteMovieCounterUseCase
) : BaseViewModel() {

    private val counterMovieFavorite = MutableLiveData<SimpleResult<Int>>()
    fun counterMovieFavorite() = counterMovieFavorite as LiveData<SimpleResult<Int>>

    init {
        getData()
    }

    private fun getData() {
        ioLaunch {
            favoriteMovieCounterUseCase.execute().collect {
                counterMovieFavorite.postValue(it)
            }
        }
    }
}