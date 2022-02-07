package com.bignerdranch.android.faci.datalayer.repos

import com.bignerdranch.android.faci.datalayer.room.MovieDao
import com.bignerdranch.android.faci.datalayer.room.dto.FavoriteMovieWithCast
import com.bignerdranch.android.faci.domain.FavoritesRepository
import com.bignerdranch.android.faci.domain.entities.Cast
import com.bignerdranch.android.faci.domain.entities.MovieDetails
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class FavoritesRepositoryImpl @Inject constructor(
    private val movieDao: MovieDao
) :
    FavoritesRepository {


    override fun addToFavorite(movie: MovieDetails): Completable {
        return movieDao.insert(movie)
    }

    override fun deleteFromFavorite(movie: MovieDetails): Completable {
        return movieDao.delete(movie)
    }

    override fun getFavoriteList(): Single<List<MovieDetails>> {
        val favoriteMoviesSingle: Single<List<FavoriteMovieWithCast>> = movieDao.getAllMovies()
        return favoriteMoviesSingle.map { favoriteMovies ->
            val result = mutableListOf<MovieDetails>()
            for (favoriteMovie in favoriteMovies)
                result.add(
                    MovieDetails(
                        id = favoriteMovie.movieId,
                        title = favoriteMovie.title,
                        poster_path = favoriteMovie.poster_path,
                        casts = favoriteMovie.casts.map { favoriteCast ->
                            Cast(
                                id = favoriteCast.castId,
                                name = favoriteCast.name,
                                profile_path = favoriteCast.profile_path
                            )
                        },
                        isInFavorite = true,
                        release_date = favoriteMovie.release_date,
                        overview = favoriteMovie.overview,
                        runtime = favoriteMovie.runtime,
                        original_title = favoriteMovie.original_title,
                        vote_average = favoriteMovie.vote_average
                    )
                )
            result
        }
    }
}