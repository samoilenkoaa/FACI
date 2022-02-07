package com.bignerdranch.android.faci.domain.usecases

import com.bignerdranch.android.faci.domain.FavoritesRepository
import com.bignerdranch.android.faci.domain.entities.MovieDetails
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class AddFavoriteMovieUseCase @Inject constructor(private val favoritesRepository: FavoritesRepository) {

    fun addToFavorite(movie: MovieDetails): Completable {
        return favoritesRepository.addToFavorite(movie)
    }
}