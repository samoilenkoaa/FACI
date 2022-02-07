package com.bignerdranch.android.faci.ui.favorites.favoritesRecyclerVIew

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.faci.databinding.FavoriteItemBinding
import com.bignerdranch.android.faci.domain.entities.MovieDetails
import com.bignerdranch.android.faci.utils.glide.loadImage
import com.zerobranch.layout.SwipeLayout

class FavoriteViewHolder(
    itemView: View,
    private val onItemClickListenerFavorites: (MovieDetails) -> Unit,
    private val onItemClickDelete: (MovieDetails) -> Unit,
) : RecyclerView.ViewHolder(itemView) {

    companion object {
        private const val URL_ADDRESS = "https://image.tmdb.org/t/p/original"
        private const val RELEASE_DATE_TEXT = "Release date:"
    }
    private val binding: FavoriteItemBinding = FavoriteItemBinding.bind(itemView)

    fun bind(movie: MovieDetails) {
        binding.titleTextView.text = movie.title
        binding.dateTextView.text = "${RELEASE_DATE_TEXT} ${movie.release_date}"
        loadImage(itemView.context, URL_ADDRESS + movie.poster_path, binding.imageView)

        if (binding.rightView != null) {
            binding.rightView.setOnClickListener(View.OnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    onItemClickDelete(movie)
                }
            })
        }

        binding.swipeLayout.setOnActionsListener(object : SwipeLayout.SwipeActionsListener {
            override fun onOpen(direction: Int, isContinuous: Boolean) {
                if (direction == SwipeLayout.LEFT && isContinuous) {
                    if (adapterPosition != RecyclerView.NO_POSITION) {

                    }
                }
            }

            override fun onClose() {

            }

        })

        binding.dragItem.setOnClickListener {
            onItemClickListenerFavorites(movie)
        }
    }

}