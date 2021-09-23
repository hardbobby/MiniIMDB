package com.hardbobby.miniimdb.presentation.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hardbobby.domain.common.Result
import com.hardbobby.domain.common.SimpleResult
import com.hardbobby.domain.dto.modelview.MovieItemModelView
import com.hardbobby.miniimdb.presentation.base.BaseViewModel
import com.hardbobby.miniimdb.presentation.common.Event
import com.hardbobby.miniimdb.presentation.utils.MutableLiveEvent

/**
 * Created by Bobby.Hardian on 22/09/2021.
 */
abstract class MovieListViewModel : BaseViewModel() {

    var popularMovieList = mutableListOf<MovieItemModelView>()
        private set

    open var isFromAPI = true

    protected var currentPage = 1
    protected var _movieData =
        MutableLiveData<SimpleResult<List<MovieItemModelView>>>(Result.State.Loading)
    val movieData = _movieData as LiveData<SimpleResult<List<MovieItemModelView>>>

    val showMovieDetail = MutableLiveEvent<Int>()

    protected val _loadingNextPage = MutableLiveEvent<Boolean>()
    fun loading() = _loadingNextPage as LiveData<Event<Boolean>>

    fun refreshData() {
        onResetData()
        getData()
    }

    private fun onResetData() {
        currentPage = 1
        popularMovieList = mutableListOf()
    }

    fun fetchNextPage() {
        if (_loadingNextPage.value?.peekContent() == false && isFromAPI) {
            _loadingNextPage.postValue(Event(true))
            currentPage++
            getData()
        }
    }

    fun addListData(data: List<MovieItemModelView>) {
        popularMovieList.addAll(data)
        popularMovieList = popularMovieList.distinctBy {
            listOf(it.idMovie, it.posterUrl)
        }.toMutableList()
    }

    abstract fun getData()

}