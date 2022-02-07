package com.bignerdranch.android.faci.ui.genresDetail.genresDetailsRecyclerView

import androidx.recyclerview.widget.DiffUtil
import com.bignerdranch.android.faci.domain.entities.Movie

class MovieDiffUtilCallback: DiffUtil.ItemCallback<Movie>(){
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}