package com.bignerdranch.android.faci.ui.genres

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bignerdranch.android.faci.datalayer.remote.dto.genres.listOfGenres.GenresList
import com.bignerdranch.android.faci.domain.usecases.GetListOFGenresUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class GenresViewModel @Inject constructor(
    private val getListOFGenresUseCase: GetListOFGenresUseCase,
) :
    ViewModel() {

    private val disposables: CompositeDisposable = CompositeDisposable()

    private val _listGenresLiveData = MutableLiveData<GenresList>()
    val listGenresLiveData: LiveData<GenresList> = _listGenresLiveData

    private val _loadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean> = _loadingLiveData

    fun getListOfGenres() {
        _loadingLiveData.value = true
        getListOFGenresUseCase.getListOfGenres().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe({
                _loadingLiveData.value = false
                _listGenresLiveData.value = it
            }, {
                _loadingLiveData.value = false
            }).also {
                disposables.add(it)
            }
    }

    override fun onCleared() {
        disposables.clear()
    }

}