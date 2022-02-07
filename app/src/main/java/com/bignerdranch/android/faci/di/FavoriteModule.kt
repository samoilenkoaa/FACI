package com.bignerdranch.android.faci.di

import android.content.Context
import androidx.room.Room
import com.bignerdranch.android.faci.datalayer.remote.RemoteDataSource
import com.bignerdranch.android.faci.datalayer.repos.FavoritesRepositoryImpl
import com.bignerdranch.android.faci.datalayer.repos.MovieRepositoryImpl
import com.bignerdranch.android.faci.datalayer.room.Database
import com.bignerdranch.android.faci.datalayer.room.MovieDao
import com.bignerdranch.android.faci.domain.FavoritesRepository
import com.bignerdranch.android.faci.domain.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object FavoriteModule {

    @Singleton
    @Provides
    fun provideFavoriteRepository(
        movieDao: MovieDao
    ): FavoritesRepository {
        return FavoritesRepositoryImpl(movieDao)
    }


    @Singleton
    @Provides
    fun provideCinemaRepository(
        dataSource: RemoteDataSource,
        movieDao: MovieDao
    ): MovieRepository {
        return MovieRepositoryImpl(dataSource,movieDao)
    }


    @Singleton
    @Provides
    fun provideMovieDao(@ApplicationContext context: Context): MovieDao {
        val database = Room.databaseBuilder(context, Database::class.java, "db").build()
        val movieDao = database.favoriteDao()
        return movieDao
    }

}