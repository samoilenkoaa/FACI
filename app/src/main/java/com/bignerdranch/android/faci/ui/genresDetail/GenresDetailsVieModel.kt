package com.bignerdranch.android.faci.ui.genresDetail

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava3.flowable
import com.bignerdranch.android.faci.domain.entities.Movie
import com.bignerdranch.android.faci.domain.usecases.GetMovieListWithGenreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

@HiltViewModel
class GenresDetailsVieModel @Inject constructor(
    private val getMovieListWithGenreUseCase: GetMovieListWithGenreUseCase,

    ) : ViewModel() {

    companion object {
        private const val PAGE_SIZE = 20
        private const val MAX_PAGE_SIZE = 30
        private const val PREFETCH_DISTANCE = 5
        private const val INITIAL_LOAD_SIZE = 40
    }

    fun getMovies(genreId: Int): Flowable<PagingData<Movie>> {
        val movieSource = MovieWithGenresSource(getMovieListWithGenreUseCase, genreId)
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = true,
                maxSize = MAX_PAGE_SIZE,
                prefetchDistance = PREFETCH_DISTANCE,
                initialLoadSize = INITIAL_LOAD_SIZE),
            pagingSourceFactory = { movieSource }
        ).flowable
    }
}