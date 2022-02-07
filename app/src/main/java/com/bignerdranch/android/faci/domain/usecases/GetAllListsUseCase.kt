package com.bignerdranch.android.faci.domain.usecases

import com.bignerdranch.android.faci.domain.MovieRepository
import com.bignerdranch.android.faci.ui.home.homeRecyclerView.base.CategoryWithListItem
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetAllListsUseCase @Inject constructor(private val repository: MovieRepository) {

    fun getAllLists(): Single<List<CategoryWithListItem>> {
        return repository.getAllLists()
    }

}