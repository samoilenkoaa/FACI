package com.bignerdranch.android.faci.domain.usecases

import com.bignerdranch.android.faci.domain.MovieRepository
import com.bignerdranch.android.faci.domain.entities.Movie
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetMovieListUseCase @Inject constructor(private val repository: MovieRepository) {

    fun getNowPlayingMovieList(): Single<List<Movie>> {
        return repository.getNowPlayingMovieList()
    }

    fun getPopularMovieList(): Single<List<Movie>> {
        return repository.getPopularMovieList()
    }

    fun getTopRatedMovieList(): Single<List<Movie>> {
        return repository.getTopRatedMovieList()
    }

    fun getUpcomingMovieList(): Single<List<Movie>> {
        return repository.getUpcomingMovieList()
    }
}