package com.example.themovieapp.di

import android.content.Context
import androidx.room.Room
import com.example.themovieapp.ApiCredentials
import com.example.themovieapp.data.MovieRepository
import com.example.themovieapp.data.local.AppDatabase
import com.example.themovieapp.data.local.MovieDao
import com.example.themovieapp.movieHome.TMDBService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(ApiCredentials.baseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideTMDBService(retrofit: Retrofit): TMDBService =
        retrofit.create(TMDBService::class.java)

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "movie-db"
        ).build()

    @Provides
    fun provideMovieDao(database: AppDatabase): MovieDao =
        database.movieDao()

}