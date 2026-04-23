package com.example.themovieapp.movieHome

import com.example.themovieapp.data.MovieResponse
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBService {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "pt-BR"

     ): MovieResponse

    @JsonClass(generateAdapter = true)
    data class MovieImagesResponse(
        val backdrops: List<ImageItem>
    )

    @JsonClass(generateAdapter = true)
    data class ImageItem(
        @Json(name = "file_path") val filePath: String
    )

    @GET("movie/{movie_id}/images")
    suspend fun getMovieImages(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): MovieImagesResponse
}