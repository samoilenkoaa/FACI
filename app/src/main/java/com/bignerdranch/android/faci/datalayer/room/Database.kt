package com.bignerdranch.android.faci.datalayer.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bignerdranch.android.faci.datalayer.room.dto.FavoriteMovie
import com.bignerdranch.android.faci.datalayer.room.tables.FavoriteCastsTable
import com.bignerdranch.android.faci.datalayer.room.tables.MovieCastTable

@Database(entities = [FavoriteMovie::class, FavoriteCastsTable::class, MovieCastTable::class],
    version = 1,
    exportSchema = false)
abstract class Database : RoomDatabase() {
    abstract fun favoriteDao(): MovieDao
}