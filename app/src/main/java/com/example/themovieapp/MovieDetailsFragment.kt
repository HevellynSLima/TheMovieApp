package com.example.themovieapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.navGraphViewModels
import com.example.themovieapp.databinding.FragmentMovieDetailsBinding
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MovieDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MovieDetailsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    //Configura o ViewBinding
    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel by navGraphViewModels<MovieViewModel>(R.id.movie_graph){
        defaultViewModelProviderFactory
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.filmeSelecionado.observe(viewLifecycleOwner){
            filme -> binding.tituloSinopse.text = filme.content
            binding.sinopse.text = filme.details// Use aqui a sinopse completa
            binding.imageView3.setImageResource(filme.imageRes)

            binding.Avaliacao.text =  "Avaliação: ${filme.avaliacao}/10⭐"

            val carousel= binding.imageCarousel
            val list = mutableListOf<CarouselItem>()

            filme.galeria.forEach { imageId ->
                list.add(CarouselItem(imageId))
            }
            carousel.setData(list)

        }
    }


}