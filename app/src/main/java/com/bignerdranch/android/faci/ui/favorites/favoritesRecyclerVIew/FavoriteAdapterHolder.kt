package com.bignerdranch.android.faci.ui.favorites.favoritesRecyclerVIew

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bignerdranch.android.faci.R
import com.bignerdranch.android.faci.domain.entities.MovieDetails

class FavoriteAdapterHolder(

    private val onItemClickListenerFavorites: (MovieDetails) -> Unit,
    private val onItemClickDelete: (MovieDetails) -> Unit,
) : ListAdapter<MovieDetails, FavoriteViewHolder>(DiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(
            LayoutInflater.from(parent.context)
            .inflate(R.layout.favorite_item, parent, false),
            onItemClickListenerFavorites,
            onItemClickDelete)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}