package com.example.kirozh.movieapplication

import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import java.io.IOException

/**
 * @author Kirill Ozhigin on 14.12.2021
 */
const val PAGING_START_INDEX = 0
const val PAGING_MAX_RETURN_NUM = 20

class MoviePagingSource(
    private val retrofitApi: RetrofitApi
) : PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        val anchorPosition = state.anchorPosition ?: return null

        val page = state.closestPageToPosition(anchorPosition) ?: return null

        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val position = params.key ?: PAGING_START_INDEX

        return try {
            val response = retrofitApi.getMovies(PAGING_MAX_RETURN_NUM * position)

            val movies = response.results

            LoadResult.Page(
                data = movies,
                prevKey = if (position == PAGING_START_INDEX) null else position - 1,
                nextKey = if (movies.isEmpty()) null else position + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}