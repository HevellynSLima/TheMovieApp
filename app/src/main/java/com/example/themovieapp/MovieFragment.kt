package com.example.themovieapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.example.themovieapp.databinding.FragmentMovieDetailsBinding
import com.example.themovieapp.databinding.FragmentMovieListBinding
import com.example.themovieapp.placeholder.PlaceholderContent

class MovieFragment : Fragment(), MovieItemListener {

    private var _binding: FragmentMovieListBinding?= null
    private val binding get()= _binding!!

    // ViewModel associado ao Grafo de Navegação
    private val viewModel by navGraphViewModels<MovieViewModel>(R.id.movie_graph) {
        defaultViewModelProviderFactory
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieListBinding.inflate(inflater,
            container,
            false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.listaFilme.observe(viewLifecycleOwner){ lista ->
            binding.list.adapter = MyItemRecyclerViewAdapter(lista,this)
        }

        viewModel.estado.observe(viewLifecycleOwner){
            status -> when (status){
                DataState.Carregando -> {
                    binding.loadingProgress.visibility = View.VISIBLE
                    binding.list.visibility = View.GONE
                }
            DataState.Sucesso -> {
                binding.loadingProgress.visibility = View.GONE
                binding.list.visibility = View.VISIBLE
            }
            DataState.Erro ->{
                binding.loadingProgress.visibility = View.GONE
                Toast.makeText(context,
                    "Erro ao carregar filmes",
                    Toast.LENGTH_SHORT
                ).show()
            }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onItemSelected(position: Int) {
        // Navegação para os detalhes
        val filme = viewModel.listaFilme.value?.get(position)

        filme?.let {
            viewModel.selecionarFilme(it)
            findNavController().navigate(R.id.movieDetailsFragment)
        }
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