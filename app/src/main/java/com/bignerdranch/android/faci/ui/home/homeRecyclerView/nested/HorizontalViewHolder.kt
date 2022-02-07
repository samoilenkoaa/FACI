package com.bignerdranch.android.faci.ui.home.homeRecyclerView.nested

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.faci.R
import com.bignerdranch.android.faci.databinding.MovieItemBinding
import com.bignerdranch.android.faci.domain.entities.Movie
import com.bignerdranch.android.faci.utils.glide.loadImage

class HorizontalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val binding: MovieItemBinding = MovieItemBinding.bind(itemView)

    fun onBind(movie: Movie) {
        binding.textViewTitle.text = movie.title
        binding.textViewVoteAverage.text = movie.vote_average.toString()

        loadImage(itemView.context,
            URL_ADDRESS + movie.poster_path,
            R.color.primaryColor,
            binding.imageViewPoster)
    }

    companion object {
        private const val URL_ADDRESS = "https://image.tmdb.org/t/p/original"
    }
}