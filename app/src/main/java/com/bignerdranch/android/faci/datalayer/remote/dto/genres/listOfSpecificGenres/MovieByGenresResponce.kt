package com.bignerdranch.android.faci.datalayer.remote.dto.genres.listOfSpecificGenres

import com.bignerdranch.android.faci.datalayer.remote.MovieFromApi

data class MovieByGenresResponce(
    val page: Int,
    val results: List<MovieFromApi>,
    val totalPages: Int
)