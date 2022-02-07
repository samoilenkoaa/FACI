package com.bignerdranch.android.faci.datalayer.repos

import com.bignerdranch.android.faci.datalayer.remote.RemoteDataSource
import com.bignerdranch.android.faci.datalayer.remote.dto.genres.listOfGenres.GenresList
import com.bignerdranch.android.faci.datalayer.remote.dto.video.VideoResponse
import com.bignerdranch.android.faci.datalayer.room.MovieDao
import com.bignerdranch.android.faci.domain.MovieRepository
import com.bignerdranch.android.faci.domain.entities.Cast
import com.bignerdranch.android.faci.domain.entities.Movie
import com.bignerdranch.android.faci.domain.entities.MovieDetails
import com.bignerdranch.android.faci.ui.home.homeRecyclerView.base.CategoryWithListItem
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val dataSource: RemoteDataSource,
    private val favoriteDao: MovieDao
) : MovieRepository {

    override fun getNowPlayingMovieList(): Single<List<Movie>> {
        return dataSource.getNowPlaying().map { responseResult ->
            responseResult.results.map { movieFromApi ->
                Movie(
                    id = movieFromApi.id,
                    overview = movieFromApi.overview,
                    poster_path = movieFromApi.poster_path,
                    release_date = movieFromApi.release_date,
                    title = movieFromApi.title,
                    vote_average = movieFromApi.vote_average
                )
            }
        }
    }

    override fun getPopularMovieList(): Single<List<Movie>> {
        return dataSource.getPopular().map { responseResult ->
            responseResult.results.map { movieFromApi ->
                Movie(
                    id = movieFromApi.id,
                    overview = movieFromApi.overview,
                    poster_path = movieFromApi.poster_path,
                    release_date = movieFromApi.release_date,
                    title = movieFromApi.title,
                    vote_average = movieFromApi.vote_average
                )
            }
        }
    }

    override fun getTopRatedMovieList(): Single<List<Movie>> {
        return dataSource.getTopRated().map { responseResult ->
            responseResult.results.map { movieFromApi ->
                Movie(
                    id = movieFromApi.id,
                    overview = movieFromApi.overview,
                    poster_path = movieFromApi.poster_path,
                    release_date = movieFromApi.release_date,
                    title = movieFromApi.title,
                    vote_average = movieFromApi.vote_average
                )
            }
        }
    }

    override fun getUpcomingMovieList(): Single<List<Movie>> {
        return dataSource.getUpcoming().map { responseResult ->
            responseResult.results.map { movieFromApi ->
                Movie(
                    id = movieFromApi.id,
                    overview = movieFromApi.overview,
                    poster_path = movieFromApi.poster_path,
                    release_date = movieFromApi.release_date,
                    title = movieFromApi.title,
                    vote_average = movieFromApi.vote_average

                )
            }
        }
    }

    override fun getDetailMovie(id: Int): Single<MovieDetails> {
        val favoritesMoviesSingle = favoriteDao.getAllMovies()
        val castsSingle = dataSource.getCrewForMovie(id)
        val movieDetailsSingle = dataSource.getDetailMovie(id)

        return Single.zip(movieDetailsSingle, favoritesMoviesSingle, castsSingle) {
                responseResult, favoritesMovies, casts ->
            val isInFavorite = favoritesMovies.find { it.movieId == id } != null
            MovieDetails(
                id = responseResult.id,
                title = responseResult.title,
                poster_path = responseResult.poster_path,
                casts = casts.castFromApi.map { castFromApi ->
                    Cast(
                        id = castFromApi.id,
                        name = castFromApi.name,
                        profile_path = castFromApi.profile_path
                    )
                },
                isInFavorite = isInFavorite,
                release_date = responseResult.release_date,
                overview = responseResult.overview,
                runtime = responseResult.runtime,
                original_title = responseResult.original_title,
                vote_average = responseResult.vote_average
            )
        }

    }


    override fun getVideo(id: Int): Single<VideoResponse> {
        return dataSource.getVideo(id)
    }

    override fun getAllLists(): Single<List<CategoryWithListItem>> {
        return Single.zip(
            getNowPlayingMovieList(),
            getPopularMovieList(),
            getTopRatedMovieList(),
            getUpcomingMovieList()
        ) { NowPlaying, Popular, TopRated, Upcoming ->
            listOf(
                CategoryWithListItem(
                    CATEGORY_TITLE_NOW_PLAYING,
                    CATEGORY_DESCRIPTION_NOW_PLAYING,
                    NowPlaying
                ),
                CategoryWithListItem(CATEGORY_TITLE_POPULAR, CATEGORY_DESCRIPTION_POPULAR, Popular),
                CategoryWithListItem(
                    CATEGORY_TITLE_TOP_RATED,
                    CATEGORY_DESCRIPTION_TOP_RATED,
                    TopRated
                ),
                CategoryWithListItem(
                    CATEGORY_TITLE_UPCOMING,
                    CATEGORY_DESCRIPTION_UPCOMING,
                    Upcoming
                ),
            )
        }
    }

    override fun getListOfGenres(): Single<GenresList> {
        return dataSource.getAllGenres()
    }

    override fun getListMovieWithGenre(page: Int, id: Int): Single<List<Movie>> {
        return dataSource.getMoviesByGenreId(page, id).map { responseResult ->
            responseResult.results.map { movieWithSpecificGenres ->
                Movie(
                    id = movieWithSpecificGenres.id,
                    overview = movieWithSpecificGenres.overview,
                    poster_path = movieWithSpecificGenres.poster_path,
                    release_date = movieWithSpecificGenres.release_date,
                    title = movieWithSpecificGenres.title,
                    vote_average = movieWithSpecificGenres.vote_average
                )
            }
        }
    }

    companion object {
        private const val CATEGORY_TITLE_NOW_PLAYING = "NowPlaying"
        private const val CATEGORY_TITLE_POPULAR = "Popular"
        private const val CATEGORY_TITLE_TOP_RATED = "TopRated"
        private const val CATEGORY_TITLE_UPCOMING = "Upcoming"

        private const val CATEGORY_DESCRIPTION_NOW_PLAYING = "Actual films of the world of cinema"
        private const val CATEGORY_DESCRIPTION_POPULAR = "Popular at all times"
        private const val CATEGORY_DESCRIPTION_TOP_RATED = "Highest ratings from a viewer"
        private const val CATEGORY_DESCRIPTION_UPCOMING = "Coming soon on all screens"
    }
}
