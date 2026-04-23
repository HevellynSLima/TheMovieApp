package com.example.themovieapp.movieDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.navGraphViewModels
import com.bumptech.glide.Glide
import com.example.themovieapp.databinding.FragmentMovieDetailsBinding
import com.example.themovieapp.MovieViewModel
import com.example.themovieapp.R
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem


class MovieDetailsFragment : Fragment() {
    private val viewModel by navGraphViewModels<MovieViewModel>(R.id.movie_graph){
        defaultViewModelProviderFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding : FragmentMovieDetailsBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_movie_details,
            container,
            false)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val args = MovieDetailsFragmentArgs.fromBundle(requireArguments())
        val movie = args.movie

        binding.tituloSinopse.text = movie.title
        binding.contentText.text = movie.overview ?: "Sinopse não disponível"

        Glide.with(this)
            .load(movie.getImageUrl())
            .into(binding.imageView3)

        val imageList = mutableListOf<CarouselItem>()

        movie.posterPath?.let {
            val imageUrl = movie.getImageUrl()
            imageList.add(CarouselItem(imageUrl = imageUrl))
            imageList.add(CarouselItem(imageUrl = imageUrl))
            imageList.add(CarouselItem(imageUrl = imageUrl))
        }

        binding.imageCarousel.setData(imageList)

        // Inflate the layout for this fragment
        return binding.root
    }



}