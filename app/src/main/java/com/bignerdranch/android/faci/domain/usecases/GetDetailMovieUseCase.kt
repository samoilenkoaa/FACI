package com.bignerdranch.android.faci.domain.usecases

import com.bignerdranch.android.faci.domain.MovieRepository
import com.bignerdranch.android.faci.domain.entities.MovieDetails
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetDetailMovieUseCase @Inject constructor(private val repository: MovieRepository) {

    fun getDetailMovie(id: Int): Single<MovieDetails> {
        return repository.getDetailMovie(id)
    }
}