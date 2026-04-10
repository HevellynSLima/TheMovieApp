package com.example.themovieapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.themovieapp.placeholder.PlaceholderContent

class MovieViewModel: ViewModel() {
    val filmeSelecionado = MutableLiveData<PlaceholderContent.PlaceholderItem>()
    fun selecionarFilme (filme: PlaceholderContent.PlaceholderItem){
        filmeSelecionado.value = filme
    }
}