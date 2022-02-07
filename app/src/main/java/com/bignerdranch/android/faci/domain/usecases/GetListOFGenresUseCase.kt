package com.bignerdranch.android.faci.domain.usecases

import com.bignerdranch.android.faci.datalayer.remote.dto.genres.listOfGenres.GenresList
import com.bignerdranch.android.faci.domain.MovieRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetListOFGenresUseCase @Inject constructor(private val repository: MovieRepository) {

    fun getListOfGenres(): Single<GenresList> {
        return repository.getListOfGenres()
    }
}