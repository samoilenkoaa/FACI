package com.bignerdranch.android.faci.datalayer.room

import androidx.room.*
import com.bignerdranch.android.faci.datalayer.room.dto.FavoriteMovie
import com.bignerdranch.android.faci.datalayer.room.dto.FavoriteMovieWithCast
import com.bignerdranch.android.faci.datalayer.room.tables.FavoriteCastsTable
import com.bignerdranch.android.faci.datalayer.room.tables.MovieCastTable
import com.bignerdranch.android.faci.domain.entities.MovieDetails
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface MovieDao {

    @Query("SELECT * FROM FavoriteFilms")
    fun getAllMovies(): Single<List<FavoriteMovieWithCast>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(favoriteMovie: FavoriteMovie): Completable

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(favoriteCast: FavoriteCastsTable): Completable

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(movieCastTable: MovieCastTable): Completable

    @Delete()
    fun delete(favoriteMovie: FavoriteMovie): Completable


    fun insert(movieDetails: MovieDetails): Completable {
        val list = mutableListOf<Completable>()
        list.add(insert(convertMovieDetailsToFavorite(movieDetails)))
        for (cast in movieDetails.casts) {
            list.add(insert(FavoriteCastsTable(cast.id, cast.name, cast.profile_path)))
            list.add(insert(MovieCastTable(movieDetails.id, cast.id)))
        }
        return Completable.concat(list)
    }

    fun delete(movieDetails: MovieDetails): Completable {
        return delete(convertMovieDetailsToFavorite(movieDetails))
    }

    private fun convertMovieDetailsToFavorite(movieDetails: MovieDetails): FavoriteMovie {
        return FavoriteMovie(
            movieId = movieDetails.id,
            title = movieDetails.title,
            poster_path = movieDetails.poster_path,
            release_date = movieDetails.release_date,
            overview = movieDetails.overview,
            runtime = movieDetails.runtime,
            original_title = movieDetails.original_title,
            vote_average = movieDetails.vote_average,
        )
    }
}