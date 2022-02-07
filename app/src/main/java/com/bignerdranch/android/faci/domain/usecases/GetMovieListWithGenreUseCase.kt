package com.bignerdranch.android.faci.domain.usecases

import com.bignerdranch.android.faci.domain.MovieRepository
import com.bignerdranch.android.faci.domain.entities.Movie
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetMovieListWithGenreUseCase @Inject constructor(private val repository: MovieRepository) {

    fun getListMovieWithGenre(page: Int, id: Int): Single<List<Movie>> {
        return repository.getListMovieWithGenre(page, id)
    }
}