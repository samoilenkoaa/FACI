package com.bignerdranch.android.faci.ui.genres.genresRecyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.faci.R
import com.bignerdranch.android.faci.datalayer.remote.dto.genres.listOfGenres.GenreFromApi

interface GenresAdapterListener{
    fun onGenreClick(genre: GenreFromApi)
}

class GenresAdapter( private val listener: GenresAdapterListener) : RecyclerView.Adapter<GenreViewHolder>() {

    var myData: List<GenreFromApi> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.genres_item, parent, false)
        return GenreViewHolder(view)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        val genre = myData[position]
        holder.bind(genre)

        holder.itemView.setOnClickListener {
            listener.onGenreClick(genre)
        }

    }

    override fun getItemCount(): Int {
        return myData.size
    }
}