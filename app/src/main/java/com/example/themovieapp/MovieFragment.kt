package com.example.themovieapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.example.themovieapp.placeholder.PlaceholderContent

class MovieFragment : Fragment() {

    private var columnCount = 1

    // ViewModel associado ao Grafo de Navegação
    private val viewModel by navGraphViewModels<MovieViewModel>(R.id.movie_graph) {
        defaultViewModelProviderFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie_list, container, false)

        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }

                // Passamos uma lambda para tratar o clique
                adapter = MyItemRecyclerViewAdapter(PlaceholderContent.ITEMS) { position ->
                    onItemSelected(position)
                }
            }
        }
        return view
    }

    private fun onItemSelected(position: Int) {
        // Navegação para os detalhes
        val filme = PlaceholderContent.ITEMS[position]

        //Guarda o filme no viewModel Compartilhado
        viewModel.selecionarFilme(filme)

        //Navega para proxima tela
        findNavController().navigate(R.id.movieDetailsFragment)
    }

    companion object {
        const val ARG_COLUMN_COUNT = "column-count"

        @JvmStatic
        fun newInstance(columnCount: Int) =
            MovieFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}