package com.bignerdranch.android.faci.datalayer.room.tables

import androidx.room.Entity
import androidx.room.ForeignKey
import com.bignerdranch.android.faci.datalayer.room.dto.FavoriteMovie

@Entity(
    tableName = "FavoriteMovieCast",
    foreignKeys = [
        ForeignKey(
            entity = FavoriteMovie::class,
            parentColumns = ["movieId"],
            childColumns = ["movieId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = FavoriteCastsTable::class,
            parentColumns = ["castId"],
            childColumns = ["castId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    primaryKeys = ["movieId", "castId"]
)
data class MovieCastTable(
    val movieId: Int,
    val castId: Int,
)