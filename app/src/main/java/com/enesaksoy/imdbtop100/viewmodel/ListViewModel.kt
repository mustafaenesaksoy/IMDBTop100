package com.enesaksoy.imdbtop100.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enesaksoy.imdbtop100.repo.IMDBRepository
import com.enesaksoy.imdbtop100.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val repo : IMDBRepository): ViewModel() {

    private val getList = MutableLiveData<Resource<List<com.enesaksoy.imdbtop100.response.Result>>>()
    val getlist : LiveData<Resource<List<com.enesaksoy.imdbtop100.response.Result>>>
    get() = getList

    fun getRetrofitApi(){
        viewModelScope.launch {
            getList.value = repo.getMovieList()
        }
    }

    private val selectedMovie = MutableLiveData<com.enesaksoy.imdbtop100.response.Result>()
    val selectedmovie : LiveData<com.enesaksoy.imdbtop100.response.Result>
        get() = selectedMovie

    fun setSelectedMovie(movie : com.enesaksoy.imdbtop100.response.Result){
        selectedMovie.postValue(movie)
    }
}