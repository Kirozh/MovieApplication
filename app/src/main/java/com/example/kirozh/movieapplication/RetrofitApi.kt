package com.example.kirozh.movieapplication

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author Kirill Ozhigin on 13.12.2021
 */
interface RetrofitApi {

    @GET("all.json?api-key=$CLIENT_ID")
    suspend fun getMovies(
        @Query("offset") offset: Int
    ): MovieResponseModel

    companion object {
        const val CLIENT_ID = BuildConfig.api_key
        var retrofitService: RetrofitApi? = null

        fun getInstance(): RetrofitApi {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://api.nytimes.com/svc/movies/v2/reviews/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitApi::class.java)
            }

            return retrofitService!!
        }
    }
}