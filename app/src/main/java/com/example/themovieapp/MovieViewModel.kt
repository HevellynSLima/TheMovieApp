package com.example.themovieapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themovieapp.placeholder.PlaceholderContent
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MovieViewModel: ViewModel() {

    val listaFilme = MutableLiveData<List<PlaceholderContent.PlaceholderItem>>()
    val estado = MutableLiveData<DataState>()
    val filmeSelecionado = MutableLiveData<PlaceholderContent.PlaceholderItem>()

    init {
        carregarFilmes()

    }

    private fun carregarFilmes(){
        viewModelScope.launch {
            estado.value = DataState.Carregando

            delay(2000)

            listaFilme.value = PlaceholderContent.ITEMS
            estado.value = DataState.Sucesso
        }
    }
    fun selecionarFilme (filme: PlaceholderContent.PlaceholderItem){
        filmeSelecionado.value = filme
    }

}