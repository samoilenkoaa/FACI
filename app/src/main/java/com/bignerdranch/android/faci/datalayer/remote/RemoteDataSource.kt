package com.bignerdranch.android.faci.datalayer.remote

import com.bignerdranch.android.faci.BuildConfig
import com.bignerdranch.android.faci.datalayer.remote.dto.detail.MovieDetailResponse
import com.bignerdranch.android.faci.datalayer.remote.dto.detail.crew.MovieCrewResponse
import com.bignerdranch.android.faci.datalayer.remote.dto.genres.listOfGenres.GenresList
import com.bignerdranch.android.faci.datalayer.remote.dto.genres.listOfSpecificGenres.MovieByGenresResponce
import com.bignerdranch.android.faci.datalayer.remote.dto.video.VideoResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor() {

    companion object {
        private const val LANGUAGE_ENG = "eng-ENG"
        private const val BASE_URL = "https://api.themoviedb.org"
    }

    private var retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()

    private var retrofitService = retrofit.create(ApiService::class.java)



    fun getPopular(): Single<ApiResponse> {
        return retrofitService.getPopular(BuildConfig.CINEMA_API_KEY)
    }

    fun getTopRated(): Single<ApiResponse> {
        return retrofitService.getTopRated(BuildConfig.CINEMA_API_KEY)
    }

    fun getUpcoming(): Single<ApiResponse> {
        return retrofitService.getUpcoming(BuildConfig.CINEMA_API_KEY)
    }

    fun getNowPlaying(): Single<ApiResponse> {
        return retrofitService.getNowPlaying(BuildConfig.CINEMA_API_KEY)
    }

    fun getMoviesByGenreId(page: Int, id: Int): Single<MovieByGenresResponce> {
        return retrofitService.getMoviesByGenreId(BuildConfig.CINEMA_API_KEY, page, id)
    }

    fun getAllLists(): Single<List<MoviesLists>> {
        return Single.zip(
            getPopular(),
            getTopRated(),
            getUpcoming(),
            getNowPlaying()
        ) { popular, topRated, upcoming, nowPlaying ->
            val popularList = MoviesLists("Popular", popular.results )
            val topRatedList = MoviesLists("Top rated", topRated.results)
            val upcomingList = MoviesLists("Upcoming", upcoming.results)
            val nowPlayingList = MoviesLists("Now playing", nowPlaying.results)
            listOf(popularList, topRatedList, upcomingList, nowPlayingList)
        }
    }

    fun getAllGenres(): Single<GenresList> {
        return retrofitService.getGenres(BuildConfig.CINEMA_API_KEY)
    }

    fun getCrewForMovie(id: Int): Single<MovieCrewResponse> {
        return retrofitService.getCrewForMovie(id, BuildConfig.CINEMA_API_KEY, LANGUAGE_ENG)
    }


    fun getVideo(id: Int): Single<VideoResponse> {
        return retrofitService.getVideo(id, BuildConfig.CINEMA_API_KEY, LANGUAGE_ENG)
    }

    fun getDetailMovie(id: Int): Single<MovieDetailResponse> {
        return retrofitService.getMovieDetails(id, LANGUAGE_ENG, BuildConfig.CINEMA_API_KEY)
    }
}