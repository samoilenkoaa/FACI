package com.bignerdranch.android.faci.ui.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bignerdranch.android.faci.domain.entities.MovieDetails
import com.bignerdranch.android.faci.domain.usecases.DeleteFavoriteMovieUseCase
import com.bignerdranch.android.faci.domain.usecases.GetAllFavoritesMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel@Inject constructor(
    private val getAllFavoritesMoviesUseCase: GetAllFavoritesMoviesUseCase,
    private val deleteFavoriteMovieUseCase: DeleteFavoriteMovieUseCase,
) : ViewModel() {

    private val disposables: CompositeDisposable = CompositeDisposable()

    val isLoadingLiveData = MutableLiveData<Boolean>(false)

    private val _favoritesListLiveData = MutableLiveData<List<MovieDetails>>()
    private var filterText = MutableLiveData("")
    val favoritesListLivedata: LiveData<List<MovieDetails>> =
        MediatorLiveData<List<MovieDetails>>().apply {
            addSource(_favoritesListLiveData) { value = filterMovies() }
            addSource(filterText) { value = filterMovies() }
        }

    private fun filterMovies(): List<MovieDetails> {
        return _favoritesListLiveData.value?.filter {
            it.title.contains(filterText.value ?: "",
                true)
        } ?: emptyList()
    }


    fun getAllFavoritesList() {
        getAllFavoritesMoviesUseCase.getAllFavoritesMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _favoritesListLiveData.value = it
            }, {

            }).also {
                disposables.add(it)
            }
    }

    fun deleteFavoriteMovie(movie: MovieDetails) {
        deleteFavoriteMovieUseCase.deleteMovie(movie).subscribeOn(Schedulers.io()).subscribe({

        }, {

        }).also {
            disposables.add(it)
        }
        getAllFavoritesList()
    }

    fun filter(text: String) {
        filterText.value = text
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }


}