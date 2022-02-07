package com.bignerdranch.android.faci.datalayer.remote

import com.bignerdranch.android.faci.datalayer.remote.dto.detail.MovieDetailResponse
import com.bignerdranch.android.faci.datalayer.remote.dto.detail.crew.MovieCrewResponse
import com.bignerdranch.android.faci.datalayer.remote.dto.genres.listOfGenres.GenresList
import com.bignerdranch.android.faci.datalayer.remote.dto.genres.listOfSpecificGenres.MovieByGenresResponce
import com.bignerdranch.android.faci.datalayer.remote.dto.video.VideoResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("3/movie/popular")
    fun getPopular(
        @Query(QUERY_API_KEY) api_key: String,
        @Query(QUERY_LANGUAGE) language: String? = LANGUAGE_ENG
    ): Single<ApiResponse>

    @GET("3/movie/top_rated")
    fun getTopRated(
        @Query(QUERY_API_KEY) api_key: String,
        @Query(QUERY_LANGUAGE) language: String? = LANGUAGE_ENG
    ): Single<ApiResponse>

    @GET("3/movie/upcoming")
    fun getUpcoming(
        @Query(QUERY_API_KEY) api_key: String,
        @Query(QUERY_LANGUAGE) language: String? = LANGUAGE_ENG
    ): Single<ApiResponse>

    @GET("3/movie/now_playing")
    fun getNowPlaying(
        @Query(QUERY_API_KEY) api_key: String,
        @Query(QUERY_LANGUAGE) language: String? = LANGUAGE_ENG
    ): Single<ApiResponse>

    @GET("3/genre/movie/list")
    fun getGenres(
        @Query(QUERY_API_KEY) api_key: String,
        @Query(QUERY_LANGUAGE) language: String? = LANGUAGE_ENG
    ): Single<GenresList>


    @GET("3/movie/{movie_id}/credits")
    fun getCrewForMovie(
        @Path(QUERY_MOVIE_ID) id: Int,
        @Query(QUERY_API_KEY) api_key: String,
        @Query(QUERY_LANGUAGE) language: String? = LANGUAGE_ENG
    ): Single<MovieCrewResponse>


    @GET("3/movie/{movie_id}/videos")
    fun getVideo(
        @Path(QUERY_MOVIE_ID) id: Int,
        @Query(QUERY_API_KEY) api_key: String,
        @Query(QUERY_LANGUAGE) language: String? = LANGUAGE_ENG
    ): Single<VideoResponse>


    @GET("3/discover/movie")
    fun getMoviesByGenreId(
        @Query(QUERY_API_KEY) api_key: String,
        @Query(PAGE) page: Int,
        @Query(QUERY_PARAM_LIST_OF_GENRE) genreId: Int,
        @Query(QUERY_LANGUAGE) language: String? = LANGUAGE_ENG,
    ): Single<MovieByGenresResponce>

    @GET("3/movie/{movie_id}")
    fun getMovieDetails(
        @Path("movie_id") id: Int,
        @Query(QUERY_LANGUAGE) language: String = LANGUAGE_ENG,
        @Query(QUERY_API_KEY) key: String,
    ): Single<MovieDetailResponse>


    companion object {
        private const val QUERY_LANGUAGE = "language"
        private const val QUERY_API_KEY = "api_key"
        private const val QUERY_MOVIE_ID = "movie_id"
        private const val LANGUAGE_ENG = "eng-ENG"
        private const val PAGE = "page"
        private const val QUERY_PARAM_LIST_OF_GENRE = "with_genres"
    }
}