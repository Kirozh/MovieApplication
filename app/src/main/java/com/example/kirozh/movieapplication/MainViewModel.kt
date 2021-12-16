package com.example.kirozh.movieapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn

/**
 * @author Kirill Ozhigin on 13.12.2021
 */
class MainViewModel(private val retrofitApi: RetrofitApi) : ViewModel() {

    fun getVmPager() = Pager(
        config = PagingConfig(
            pageSize = 25,
            enablePlaceholders = false,
            initialLoadSize = 100
        ),
        pagingSourceFactory = { MoviePagingSource(retrofitApi) }
    ).flow.cachedIn(viewModelScope)
}