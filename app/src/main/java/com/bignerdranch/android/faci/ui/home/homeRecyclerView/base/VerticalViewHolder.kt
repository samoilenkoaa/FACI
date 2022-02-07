package com.bignerdranch.android.faci.ui.home.homeRecyclerView.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.faci.databinding.ListMoviesCardBinding
import com.bignerdranch.android.faci.ui.home.homeRecyclerView.nested.HorizontalAdapter
import com.bignerdranch.android.faci.ui.home.homeRecyclerView.nested.HorizontalAdapterListener

class VerticalViewHolder(itemView: View, listener: HorizontalAdapterListener) : RecyclerView.ViewHolder(itemView) {
    private val binding: ListMoviesCardBinding = ListMoviesCardBinding.bind(itemView)
    private val adapter = HorizontalAdapter(listener)
    fun bind(moviesLists: CategoryWithListItem) {
        binding.listTitle.text = moviesLists.categoryTitle
        binding.descriptionTitle.text = moviesLists.description
        adapter.myData = moviesLists.movies
        binding.horizontalRecyclerView.adapter = adapter
    }
}