package com.bignerdranch.android.faci.ui.genres

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.bignerdranch.android.faci.databinding.GenresFragmentBinding
import com.bignerdranch.android.faci.datalayer.remote.dto.genres.listOfGenres.GenreFromApi
import com.bignerdranch.android.faci.ui.genres.genresRecyclerView.GenresAdapter
import com.bignerdranch.android.faci.ui.genres.genresRecyclerView.GenresAdapterListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GenresFragment : Fragment(), GenresAdapterListener {

    private var _binding: GenresFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: GenresViewModel by viewModels()

    private lateinit var adapter: GenresAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = GenresFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = GenresAdapter(this)
        binding.recyclerViewGenres.layoutManager = GridLayoutManager(this.context, 2)
        binding.recyclerViewGenres.adapter = adapter
        setObservers()
        viewModel.getListOfGenres()

    }

    fun setObservers() {
        viewModel.listGenresLiveData.observe(viewLifecycleOwner) { genresInfo ->

            adapter.myData = genresInfo.genresList
            adapter.notifyDataSetChanged()

        }

        viewModel.loadingLiveData.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading == true)
                showProgressBar()
            else
                hideProgressBar()
        }
    }


    fun hideProgressBar() {
        binding.progressBarGenres.visibility = View.GONE
    }

    fun showProgressBar() {
        binding.progressBarGenres.visibility = View.VISIBLE
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onGenreClick(genre: GenreFromApi) {
        val action = GenresFragmentDirections.actionGenresFragmentToGenresDetailFragment(genre.id)
        findNavController().navigate(action)
    }


}