package com.bignerdranch.android.faci.ui.genresDetail.genresDetailsRecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.faci.R
import com.bignerdranch.android.faci.databinding.GenresDetailItemBinding
import com.bignerdranch.android.faci.domain.entities.Movie
import com.bumptech.glide.Glide

class GenresDetailAdapterHolder(private val onMovieClickListener: (Movie) -> Unit) : PagingDataAdapter<Movie, GenresDetailAdapterHolder.GenresDetailViewHolder>(
    MovieDiffUtilCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenresDetailViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.genres_detail_item, parent, false)
        return GenresDetailViewHolder(view)
    }

    override fun onBindViewHolder(holder: GenresDetailViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)
        holder.itemView.setOnClickListener {
            getItem(position)?.let { view -> onMovieClickListener.invoke(view) }
        }
    }


    class GenresDetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding: GenresDetailItemBinding = GenresDetailItemBinding.bind(itemView)
        fun bind(movie: Movie?) {

            movie?.let{
                binding.titleTextView.text = movie.title
                binding.releaseDateTextView.text = "$RELEASE_DATE_TEXT ${movie.release_date}"
                binding.voteAverageTextView.text = "$VOTE_AVERAGE_TEXT ${movie.vote_average.toString()}"

                Glide
                    .with(itemView.context)
                    .load(URL_ADDRESS + movie.poster_path)
                    .fitCenter()
                    .centerCrop()
                    .into(binding.imageViewPoster)
            }

        }
    }

    companion object {
        private const val URL_ADDRESS = "https://image.tmdb.org/t/p/original"
        private const val RELEASE_DATE_TEXT = "Release date:"
        private const val VOTE_AVERAGE_TEXT = "Vote average:"

    }
}