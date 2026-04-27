package com.example.themovieapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.themovieapp.data.Movie
import com.example.themovieapp.data.MovieRepository
import com.example.themovieapp.data.local.AppDatabase
import com.example.themovieapp.movieDetails.MovieDetails
import com.example.themovieapp.movieHome.TMDBService
import kotlinx.coroutines.launch

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MovieViewModel(application: Application): AndroidViewModel(application) {

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

    private val service = retrofit.create(TMDBService::class.java)

    private val dao = AppDatabase
        .getDatabase(application)
        .movieDao()

    private val repository = MovieRepository(service,dao)

    fun getMovieData() {
        viewModelScope.launch {
                val movies = repository.getMovies()
                _movieListLiveData.postValue(movies)
        }
    }

    fun getMovieImages(movieId: Int) {
        viewModelScope.launch {
            try {
                val images = repository.getMovieImage(movieId)
                _movieImagesLiveData.postValue(images)
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
