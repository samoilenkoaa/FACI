package com.bignerdranch.android.faci.datalayer.room.tables

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FavoriteCasts")
data class FavoriteCastsTable(
    @PrimaryKey
    val castId: Int,
    val name: String,
    val profile_path: String?,
)