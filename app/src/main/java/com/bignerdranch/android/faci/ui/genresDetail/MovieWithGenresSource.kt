package com.bignerdranch.android.faci.ui.genresDetail

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import com.bignerdranch.android.faci.domain.entities.Movie
import com.bignerdranch.android.faci.domain.usecases.GetMovieListWithGenreUseCase
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class MovieWithGenresSource(
    private val getMovieListWithGenreUseCase: GetMovieListWithGenreUseCase,
    private val id: Int,
) : RxPagingSource<Int, Movie>() {


    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, Movie>> {
        val position = params.key ?: 1

        return getMovieListWithGenreUseCase.getListMovieWithGenre(position, id)
            .subscribeOn(Schedulers.io())
            .map { toLoadResult(it, position) }
            .onErrorReturn { LoadResult.Error(it) }
    }


    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? = null


    private fun toLoadResult(data: List<Movie>, position: Int): LoadResult<Int, Movie> {
        return LoadResult.Page(
            data = data,
            prevKey = if (position == 1) null else position - 1,
            nextKey = if (position == 5) null else position + 1
        )
    }
}