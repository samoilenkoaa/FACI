package com.bignerdranch.android.faci.domain.usecases

import com.bignerdranch.android.faci.domain.FavoritesRepository
import com.bignerdranch.android.faci.domain.entities.MovieDetails
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetAllFavoritesMoviesUseCase @Inject constructor(private val favoritesRepository: FavoritesRepository) {

    fun getAllFavoritesMovies(): Single<List<MovieDetails>> {
        return favoritesRepository.getFavoriteList()
    }
}