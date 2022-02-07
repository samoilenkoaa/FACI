package com.bignerdranch.android.faci.ui.details.detailRecyclerVIew

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.faci.R
import com.bignerdranch.android.faci.databinding.DetailItemBinding
import com.bignerdranch.android.faci.domain.entities.Cast
import com.bignerdranch.android.faci.utils.glide.loadImage

class DetailsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    companion object {
        private const val URL_ADDRESS = "https://image.tmdb.org/t/p/original"
    }

    val binding: DetailItemBinding = DetailItemBinding.bind(itemView)
    fun bind(cast: Cast) {
        binding.nameTextView.text = cast.name
        if (cast.profile_path != null) {
            loadImage(itemView.context,
                URL_ADDRESS + cast.profile_path,
                binding.profilePathImageView)
        } else {
            loadImage(itemView.context,
                R.drawable.ic_baseline_empty_cast_avatar_24,
                binding.profilePathImageView)
        }

    }

}