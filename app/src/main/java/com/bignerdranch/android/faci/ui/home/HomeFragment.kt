package com.bignerdranch.android.faci.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bignerdranch.android.faci.databinding.HomeFragmentBinding
import com.bignerdranch.android.faci.domain.entities.Movie
import com.bignerdranch.android.faci.ui.home.homeRecyclerView.base.VerticalAdapter
import com.bignerdranch.android.faci.ui.home.homeRecyclerView.nested.HorizontalAdapterListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment: Fragment() {

    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    private lateinit var adapter: VerticalAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = VerticalAdapter(object : HorizontalAdapterListener {
            override fun onMovieClick(movieId: Movie) {
                val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(movieId.id)
                findNavController().navigate(action)
            }

        })
        binding.recyclerViewVertical.adapter = adapter
        setObservers()
        viewModel.refreshCinema()
    }



    fun setObservers() {
        viewModel.listPopularMoviesLiveData.observe(viewLifecycleOwner) { allLists ->
            adapter.myData= allLists
            adapter.notifyDataSetChanged()
        }


        viewModel.isLoadingLiveData.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading == true)
                showProgressBar()
            else
                hideProgressBar()
        }
    }

    fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }

    fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}