package com.bignerdranch.android.faci.ui.genresDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.paging.PagingData
import com.bignerdranch.android.faci.databinding.GenresDetailFragmentBinding
import com.bignerdranch.android.faci.domain.entities.Movie
import com.bignerdranch.android.faci.ui.genresDetail.genresDetailsRecyclerView.GenresDetailAdapterHolder
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.disposables.Disposable

@AndroidEntryPoint
class GenresDetailsFragment : Fragment() {

    private var _binding: GenresDetailFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: GenresDetailsVieModel by viewModels()
    private val args: GenresDetailsFragmentArgs by navArgs()

    private val adapter = GenresDetailAdapterHolder { movie ->
        val action =
            GenresDetailsFragmentDirections.actionGenresDetailFragmentToDetailsFragment(movie.id)
        findNavController().navigate(action)
    }

    private var disposable: Disposable? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = GenresDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.genresDetailRecyclerView.adapter = adapter
        disposable = (viewModel.getMovies(args.genreId).subscribe {
            setListToAdapter(it)
        })
    }

    private fun setListToAdapter(pagingDate: PagingData<Movie>) {
        adapter.submitData(lifecycle, pagingDate)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}