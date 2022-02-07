package com.bignerdranch.android.faci.datalayer.remote.dto.genres.listOfGenres

import com.google.gson.annotations.SerializedName

data class GenresList (
    @SerializedName("genres") val genresList : List<GenreFromApi>
)