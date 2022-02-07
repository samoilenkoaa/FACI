package com.bignerdranch.android.faci.datalayer.remote

import com.google.gson.annotations.SerializedName

data class ApiResponse (
    @SerializedName("page") val page : Int,
    @SerializedName("results") val results : List<MovieFromApi>,
    @SerializedName("total_pages") val totalPages : Int,
    @SerializedName("total_results") val totalResults : Int
)