package com.example.kirozh.movieapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * @author Kirill Ozhigin on 19.11.2021
 */
class MainViewModelFactory(private val retrofitApi: RetrofitApi) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            MainViewModel(this.retrofitApi) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}
