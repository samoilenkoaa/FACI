package com.bignerdranch.android.faci.domain.usecases

import com.bignerdranch.android.faci.domain.FavoritesRepository
import com.bignerdranch.android.faci.domain.entities.MovieDetails
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class DeleteFavoriteMovieUseCase @Inject constructor(private val favoritesRepository: FavoritesRepository) {

    fun deleteMovie(movie: MovieDetails): Completable {
        return favoritesRepository.deleteFromFavorite(movie)
    }

}