package com.example.themovieapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themovieapp.data.Movie
import com.example.themovieapp.movieDetails.MovieDetails
import com.example.themovieapp.movieHome.TMDBService
import kotlinx.coroutines.launch

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MovieViewModel: ViewModel() {

    private val _movieImagesLiveData = MutableLiveData<List<String>>()
    val movieImagesLiveData: LiveData<List<String>>
        get() = _movieImagesLiveData
    private val _movieDetailsLiveData = MutableLiveData<MovieDetails>()
    val movieDetailsLiveData: LiveData <MovieDetails>
        get() = _movieDetailsLiveData

    private val _movieListLiveData = MutableLiveData <List<Movie>>()
    val movieListLiveData: LiveData <List<Movie>>
        get() = _movieListLiveData

    private val _navigationToDetailLiveData = MutableLiveData<Movie?>()
    val navigationToDetailLiveData get() = _navigationToDetailLiveData


    private val retrofit = Retrofit.Builder()
        .baseUrl(ApiCredentials.baseUrl)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    fun getMovieData() {
        viewModelScope.launch {
            try {
                val service = retrofit.create(TMDBService::class.java)

                val movieResponse = service.getPopularMovies(
                    apiKey = ApiCredentials.apiKey,
                    language = "pt-BR"
                )

                _movieListLiveData.postValue(movieResponse.results)

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getMovieImages(movieId: Int) {
        viewModelScope.launch {
            try {
                val service = retrofit.create(TMDBService::class.java)

                val imagesResponse = service.getMovieImages(
                    movieId = movieId,
                    apiKey = ApiCredentials.apiKey
                )

                val imageUrls = imagesResponse.backdrops.map {
                    "https://image.tmdb.org/t/p/w500${it.filePath}"
                }

                _movieImagesLiveData.postValue(imageUrls)

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun onMovieSelected(position: Int) {
        val movie = _movieListLiveData.value?.get(position)

        movie?.let {
            val details = MovieDetails(
                title = it.title,
                content = it.overview ?: "Sinopse não disponível",
                imageUrl = it.getImageUrl()
            )

            _movieDetailsLiveData.postValue(details)
            _navigationToDetailLiveData.postValue(it)

            getMovieImages(it.id)
        }
    }

    fun onNavigationToDetailDone() {
        _navigationToDetailLiveData.value = null
    }

    


}
