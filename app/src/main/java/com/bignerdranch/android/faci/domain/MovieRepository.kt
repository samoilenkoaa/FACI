package com.bignerdranch.android.faci.domain

import com.bignerdranch.android.faci.datalayer.remote.dto.genres.listOfGenres.GenresList
import com.bignerdranch.android.faci.datalayer.remote.dto.video.VideoResponse
import com.bignerdranch.android.faci.domain.entities.Movie
import com.bignerdranch.android.faci.domain.entities.MovieDetails
import com.bignerdranch.android.faci.ui.home.homeRecyclerView.base.CategoryWithListItem
import io.reactivex.rxjava3.core.Single

interface MovieRepository {
    fun getNowPlayingMovieList(): Single<List<Movie>>
    fun getPopularMovieList(): Single<List<Movie>>
    fun getTopRatedMovieList(): Single<List<Movie>>
    fun getUpcomingMovieList(): Single<List<Movie>>
    fun getListMovieWithGenre(page: Int, id: Int): Single<List<Movie>>
    fun getAllLists(): Single<List<CategoryWithListItem>>
    fun getVideo(id: Int): Single<VideoResponse>
    fun getListOfGenres(): Single<GenresList>
    fun getDetailMovie(id: Int): Single<MovieDetails>
}