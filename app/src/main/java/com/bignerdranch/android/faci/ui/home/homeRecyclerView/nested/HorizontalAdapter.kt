package com.bignerdranch.android.faci.ui.home.homeRecyclerView.nested

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.faci.R
import com.bignerdranch.android.faci.domain.entities.Movie

interface HorizontalAdapterListener{
    fun onMovieClick(movie: Movie)
}

class HorizontalAdapter(private val listener: HorizontalAdapterListener) : RecyclerView.Adapter<HorizontalViewHolder>() {

    var myData: List<Movie> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return HorizontalViewHolder(view)
    }

    override fun onBindViewHolder(holder: HorizontalViewHolder, position: Int) {
        val movie = myData[position]
        holder.onBind(movie)
        holder.itemView.setOnClickListener {
            listener.onMovieClick(movie)
        }
    }

    override fun getItemCount(): Int {
        return myData.size
    }

}