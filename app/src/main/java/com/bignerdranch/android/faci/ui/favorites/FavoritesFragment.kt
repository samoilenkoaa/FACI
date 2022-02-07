package com.bignerdranch.android.faci.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bignerdranch.android.faci.databinding.FavoritesFragmentBinding
import com.bignerdranch.android.faci.ui.favorites.favoritesRecyclerVIew.FavoriteAdapterHolder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment() {

    private var _binding: FavoritesFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FavoriteViewModel by viewModels()

    private val favoritesAdapter = FavoriteAdapterHolder(
        onItemClickListenerFavorites = {
            val action = FavoritesFragmentDirections.actionFavoritesFragmentToDetailsFragment(it.id)
            findNavController().navigate(action)
        },
        onItemClickDelete = {
            viewModel.deleteFavoriteMovie(it)
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FavoritesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAllFavoritesList()

        setAdapter()

        viewModel.favoritesListLivedata.observe(viewLifecycleOwner) {
            it?.let { favoriteList ->
                favoritesAdapter.submitList(favoriteList)
                if (favoriteList.isNotEmpty()) {
                    listIsNotEmptyVisibility()
                } else {
                    listIsEmptyVisibility()
                }
            }
        }

        binding.searchView.addTextChangedListener {
            viewModel.filter(it.toString())
        }

        viewModel.isLoadingLiveData.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading == true)
                showProgressBar()
            else
                hideProgressBar()
        }

    }

    fun showProgressBar() {
        binding.progressBarFavorite.visibility = View.VISIBLE
    }

    fun hideProgressBar() {
        binding.progressBarFavorite.visibility = View.GONE
    }

    private fun setAdapter() {
        binding.recyclerViewFavorite.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewFavorite.adapter = favoritesAdapter
    }

    private fun listIsEmptyVisibility() {
        binding.emptyListBackground.visibility = View.VISIBLE
        binding.recyclerViewFavorite.visibility = View.GONE
    }

    private fun listIsNotEmptyVisibility() {
        binding.recyclerViewFavorite.visibility = View.VISIBLE
        binding.emptyListBackground.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}