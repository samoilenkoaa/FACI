package com.bignerdranch.android.faci.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bignerdranch.android.faci.domain.usecases.GetAllListsUseCase
import com.bignerdranch.android.faci.ui.home.homeRecyclerView.base.CategoryWithListItem
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getAllListsUseCase: GetAllListsUseCase) :
    ViewModel() {

    private val disposables: CompositeDisposable = CompositeDisposable()

    private val _listPopularMoviesLiveData = MutableLiveData<List<CategoryWithListItem>>()
    val listPopularMoviesLiveData: LiveData<List<CategoryWithListItem>> = _listPopularMoviesLiveData

    private val _errorLiveData = MutableLiveData<Throwable>()
    val errorLiveData: LiveData<Throwable> = _errorLiveData

    private val _isLoadingLiveData = MutableLiveData<Boolean>()
    val isLoadingLiveData: LiveData<Boolean> = _isLoadingLiveData


    fun refreshCinema() {
        _isLoadingLiveData.value = true
        getAllListsUseCase.getAllLists().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe({
                _isLoadingLiveData.value = false
                _listPopularMoviesLiveData.value = it
            }, {
                _isLoadingLiveData.value = false
                _errorLiveData.value = it
            }).also {
                disposables.add(it)
            }
    }

    override fun onCleared() {
        disposables.clear()
    }


}