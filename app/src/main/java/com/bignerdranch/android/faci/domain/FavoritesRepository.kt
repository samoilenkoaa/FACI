package com.bignerdranch.android.faci.domain

import com.bignerdranch.android.faci.domain.entities.MovieDetails
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface FavoritesRepository {

    fun addToFavorite(movie: MovieDetails): Completable

    fun deleteFromFavorite(movie: MovieDetails): Completable

    fun getFavoriteList(): Single<List<MovieDetails>>


}