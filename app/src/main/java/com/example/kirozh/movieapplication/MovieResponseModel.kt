package com.example.kirozh.movieapplication

/**
 * @author Kirill Ozhigin on 13.12.2021
 */
data class MovieResponseModel(
    var status: String = "",
    var copyright: String = "",
    var has_more: Boolean = false,
    var num_results: Int = 0,
    var results: List<Movie> = emptyList()
)