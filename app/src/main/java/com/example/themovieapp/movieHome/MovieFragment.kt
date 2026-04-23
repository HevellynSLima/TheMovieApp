package com.example.themovieapp.movieHome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.themovieapp.R
import com.example.themovieapp.databinding.FragmentMovieListBinding
import com.example.themovieapp.MovieViewModel
import com.example.themovieapp.data.Movie

class MovieFragment : Fragment(), MovieItemListener {
    private val viewModel by navGraphViewModels<MovieViewModel>(R.id.movie_graph) {
        defaultViewModelProviderFactory
    }
    private lateinit var adapter: MyItemRecyclerViewAdapter
    private lateinit var binding: FragmentMovieListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = MyItemRecyclerViewAdapter(this)

        binding.list.apply {
            this.adapter = this@MovieFragment.adapter
            this.layoutManager = LinearLayoutManager(context)
        }

        initObservers()

        viewModel.getMovieData()
    }

    private fun initObservers() {
        viewModel.movieListLiveData.observe(viewLifecycleOwner) { list ->
            adapter.updateData(list)
        }

        viewModel.navigationToDetailLiveData.observe(viewLifecycleOwner) { movie ->
            if (movie != null) {
                val action = MovieFragmentDirections
                    .actionMovieFragmentToMovieDetailsFragment(movie)

                findNavController().navigate(action)
                viewModel.onNavigationToDetailDone()
            }
        }
    }

    override fun onItemSelected(position: Int) {
        viewModel.onMovieSelected(position)
    }
}

