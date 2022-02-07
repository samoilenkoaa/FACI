package com.bignerdranch.android.faci.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bignerdranch.android.faci.R
import com.bignerdranch.android.faci.databinding.DetailsFragmentBinding
import com.bignerdranch.android.faci.domain.entities.Cast
import com.bignerdranch.android.faci.domain.entities.MovieDetails
import com.bignerdranch.android.faci.ui.details.detailRecyclerVIew.DetailsAdapter
import com.bignerdranch.android.faci.utils.youtubeVideo.YoutubeLoader
import com.bignerdranch.android.faci.utils.youtubeVideo.addOnCloseListener
import com.bignerdranch.android.faci.utils.youtubeVideo.pause
import com.bignerdranch.android.faci.utils.youtubeVideo.play
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment: Fragment() {

    private var _binding: DetailsFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var youtubeLoader: YoutubeLoader

    private val viewModel: DetailsViewModel by viewModels()
    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = DetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        youtubeLoader = YoutubeLoader(lifecycle, binding.youtubePlayerView, binding.youtubeView)
        viewModel.getVideo(args.movieId)
        viewModel.videoLiveData.observe(viewLifecycleOwner) { listUrls ->
            youtubeLoader.loadVideo(listUrls)
        }

        viewModel.errorVideoLiveData.observe(viewLifecycleOwner) {

        }

        viewModel.getMovieDetail(args.movieId)

        viewModel.movieLiveData.observe(viewLifecycleOwner) {
            setAllFields(it)
            stopPlayer()
            setAdapter(it.casts)


            if (it.isInFavorite == true) {
                binding.add.setImageResource(R.drawable.ic_baseline_in_favorites_24)
            } else {
                binding.add.setImageResource(R.drawable.ic_baseline_favorites_24dp)
            }

        }

        binding.add.setOnClickListener {
            viewModel.changeFavoriteStatus()
        }

    }

    private fun setAllFields(movie: MovieDetails) {
        binding.collapsingToolbar.title = movie.original_title
        binding.collapsingToolbar.setCollapsedTitleTextColor(resources.getColor(R.color.white))
        binding.originalTitleTextView.text = movie.original_title
        binding.releaseDateTextView.text = "Release date:  ${movie.release_date}"
        binding.voteAverageTextView.text = "Vote average: ${movie.vote_average.toString()}"
        if (movie.runtime != null) {
            binding.runtimeTextView.text = "Runtime: ${movie.runtime.toString()}"
        } else {
            binding.runtimeTextView.text = "--"
        }

        binding.overviewTextView.text = movie.overview
    }

    private fun setAdapter(listCasts: List<Cast>) {
        val adapter = DetailsAdapter(listCasts)
        binding.detailsRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.detailsRecyclerView.adapter = adapter
    }


    private fun stopPlayer() {
        binding.appbar.addOnCloseListener(
            onClose = {
                binding.youtubePlayerView.pause()
            },
            onOpen = {
                binding.youtubePlayerView.play()
            })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}