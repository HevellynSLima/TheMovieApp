package com.example.themovieapp.data

import com.example.themovieapp.ApiCredentials
import com.example.themovieapp.data.local.MovieDao
import com.example.themovieapp.data.local.MovieEntity
import com.example.themovieapp.movieHome.TMDBService

class MovieRepository(
    private val service: TMDBService,
    private val dao: MovieDao) {

    suspend fun getMovies(): List<Movie> {
        try {
            val response = service.getPopularMovies(
                apiKey = ApiCredentials.apiKey,
                language = "pt-BR"
            )

            val entities = response.results.map {
                MovieEntity(
                    id = it.id,
                    title = it.title,
                    overview = it.overview ?: "",
                    posterPath = it.posterPath
                )
        }
            dao.insertMovies(entities)
        } catch (e: Exception){
        e.printStackTrace()
        }
        return dao.getMovies().map {
            Movie(
                id = it.id,
                title = it.title,
                overview = it.overview,
                posterPath = it.posterPath
            )
        }
    }

    suspend fun getMovieImage(movieId:Int): List  <String>{
        val response = service.getMovieImages(
            movieId = movieId,
            apiKey = ApiCredentials.apiKey
        )

        return response.backdrops.map {
            "https://image.tmdb.org/t/p/w500${it.filePath}"
        }
    }
}