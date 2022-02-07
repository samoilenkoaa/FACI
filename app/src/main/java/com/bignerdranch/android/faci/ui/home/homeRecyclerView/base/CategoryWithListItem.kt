package com.bignerdranch.android.faci.ui.home.homeRecyclerView.base

import com.bignerdranch.android.faci.domain.entities.Movie

class CategoryWithListItem(
    var categoryTitle: String,
    var description: String,
    var movies: List<Movie>
)