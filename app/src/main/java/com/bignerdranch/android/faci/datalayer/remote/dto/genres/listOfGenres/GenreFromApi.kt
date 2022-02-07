package com.bignerdranch.android.faci.datalayer.remote.dto.genres.listOfGenres

import com.google.gson.annotations.SerializedName

data class GenreFromApi (
    @SerializedName("id") val id : Int,
    @SerializedName("name") val name : String
)