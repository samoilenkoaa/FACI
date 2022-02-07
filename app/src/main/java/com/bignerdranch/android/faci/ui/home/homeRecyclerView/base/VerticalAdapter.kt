package com.bignerdranch.android.faci.ui.home.homeRecyclerView.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.faci.R
import com.bignerdranch.android.faci.ui.home.homeRecyclerView.nested.HorizontalAdapterListener

class VerticalAdapter(private val listener: HorizontalAdapterListener) : RecyclerView.Adapter<VerticalViewHolder>()  {
    var myData: List<CategoryWithListItem> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerticalViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_movies_card, parent, false)
        return VerticalViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: VerticalViewHolder, position: Int) {
        val cinema = myData[position]
        holder.bind(cinema)
    }

    override fun getItemCount(): Int {
        return myData.size
    }
}