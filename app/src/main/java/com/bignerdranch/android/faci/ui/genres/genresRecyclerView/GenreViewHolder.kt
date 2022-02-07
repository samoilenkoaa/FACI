package com.bignerdranch.android.faci.ui.genres.genresRecyclerView

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.faci.R
import com.bignerdranch.android.faci.databinding.GenresItemBinding
import com.bignerdranch.android.faci.datalayer.remote.dto.genres.listOfGenres.GenreFromApi
import com.bignerdranch.android.faci.utils.glide.loadImage

class GenreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val binding: GenresItemBinding = GenresItemBinding.bind(itemView)
    fun bind(genre: GenreFromApi) {
        binding.textViewTitle.text = genre.name

        when (genre.name) {
            "Action" -> loadImage(itemView.context, R.drawable.action, binding.imageViewPoster)
            "Adventure" -> loadImage(itemView.context, R.drawable.adventure, binding.imageViewPoster)
            "Animation" -> loadImage(itemView.context, R.drawable.animation, binding.imageViewPoster)
            "Comedy" -> loadImage(itemView.context, R.drawable.comedy, binding.imageViewPoster)
            "Crime" -> loadImage(itemView.context, R.drawable.crime, binding.imageViewPoster)
            "Documentary" -> loadImage(itemView.context, R.drawable.documentary, binding.imageViewPoster)
            "Drama" -> loadImage(itemView.context, R.drawable.drama, binding.imageViewPoster)
            "Family" -> loadImage(itemView.context, R.drawable.family, binding.imageViewPoster)
            "Fantasy" -> loadImage(itemView.context, R.drawable.fantasy, binding.imageViewPoster)
            "History" -> loadImage(itemView.context, R.drawable.history, binding.imageViewPoster)
            "Horror" -> loadImage(itemView.context, R.drawable.horror, binding.imageViewPoster)
            "Music" -> loadImage(itemView.context, R.drawable.music, binding.imageViewPoster)
            "Mystery" -> loadImage(itemView.context, R.drawable.my_stery, binding.imageViewPoster)
            "Romance" -> loadImage(itemView.context, R.drawable.romance, binding.imageViewPoster)
            "Science Fiction" -> loadImage(itemView.context, R.drawable.finch, binding.imageViewPoster)
            "TV Movie" -> loadImage(itemView.context, R.drawable.tv_movie, binding.imageViewPoster)
            "Thriller" -> loadImage(itemView.context, R.drawable.thriller, binding.imageViewPoster)
            "War" -> loadImage(itemView.context, R.drawable.war, binding.imageViewPoster)
            "Western" -> loadImage(itemView.context, R.drawable.western, binding.imageViewPoster)
        }
    }
}