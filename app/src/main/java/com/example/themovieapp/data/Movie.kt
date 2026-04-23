package com.example.themovieapp.data

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Movie(
    val id: Int,
    val title: String,
    val overview: String?,
    @Json(name = "poster_path") val posterPath: String?
) : Parcelable {
    fun getImageUrl() = "https://image.tmdb.org/t/p/w500$posterPath"
}