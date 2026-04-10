package com.example.themovieapp.placeholder

import com.example.themovieapp.R // Importante para reconhecer o R.drawable
import java.util.ArrayList

object PlaceholderContent {

    // Lista que o seu Adapter consome
    val ITEMS: MutableList<PlaceholderItem> = ArrayList()

    init {
        // IMPORTANTE: Agora passamos o R.drawable.nome_da_imagem como 4º parâmetro
        addItem(PlaceholderItem(
            "1",
            "Zootropolis",
            "Em um mundo de animais, uma coelha policial e um raposo golpista se unem para desvendar um mistério.",
            "Em Zootopia: Essa Cidade é o Bicho, acompanhamos a história de Judy Hopps, uma pequena fazendeira que é filha de agricultores. " +
                    "Insatisfeita com a vida no interior, ela tem sonhos maiores: se mudar para a cidade grande, Zootopia," +
                    " e se tornar a primeira coelha policial. Quando Judy consegue alcançar o seu objetivo, " +
                    "ela é designada para a sua primeira e grande missão, que é encontrar um animal perdido. " +
                    "Contando com a ajuda inesperada de Nick, uma raposa conhecida por sua malícia e infrações, " +
                    "ela descobre que existe uma conspiração que afetará toda a cidade.",
            R.drawable.zootropolis,7.4,
            listOf(R.drawable.z1, R.drawable.zootropolis1)
        ))

        addItem(PlaceholderItem(
            "2",
            "Zootropolis 2",
            "Judy e Nick retornam para enfrentar um novo caso intrigante, explorando novos bairros e desafios.",
            "Os heróis e policiais novatos Judy Hopps e Nick Wilde estão de volta para mais uma aventura extravagante pela grande metrópole animal de Zootopia. " +
                    "Em Zootopia 2, após desvendarem o maior caso da história da cidade, Judy e Nick são surpreendidos por uma ordem do Chefe Bogo: os dois detetives precisarão frequentar o programa de aconselhamento Parceiros em Crise. " +
                    "A união da dupla é colocada à prova quando surge um mistério ligado a um recém-chegado à cidade: o misterioso e venenoso réptil Gary De’Snake. Para encontrar as soluções para o caso envolvendo a víbora, " +
                    "Judy e Nick devem desvendar novas partes da cidade, sendo testados o tempo todo.",
            R.drawable.zootropolis2,7.4,
            listOf(R.drawable.zootropolis2,R.drawable.z2)// Troque pelo nome do arquivo de imagem do filme 2
        ))

        addItem(PlaceholderItem(
            "3",
            "Moana",
            "Uma jovem parte em uma missão ousada pelo oceano para salvar seu povo, com a ajuda de Maui.",
            "Moana Waialiki é uma corajosa jovem, filha única do chefe de uma tribo na Oceania, vinda de uma longa linhagem de navegadores." +
                    " Quando os pescadores de sua ilha não conseguem pescar nenhum peixe e as colheitas falham," +
                    " ela descobre que o semideus Maui causou a praga ao roubar o coração da deusa Te Fiti. " +
                    "Entusiasta das viagens marítimas, a jovem se vê querendo descobrir mais sobre seu passado e ajudar a comunidade, mesmo que a família queira proteger Moana a qualquer custo. " +
                    "Então, ela resolve partir em busca de seus ancestrais, habitantes de uma ilha mítica que ninguém sabe onde é. " +
                    "O filme é baseado em histórias da mitologia polinésia.",
            R.drawable.moana1, 7.6, // Troque pelo nome do arquivo de imagem da Moana
        listOf(R.drawable.moana, R.drawable.moana1, R.drawable.m)
            ))
    }

    private fun addItem(item: PlaceholderItem) {
        ITEMS.add(item)
    }

    // A classe de dados deve refletir exatamente o que você usa no init
    data class PlaceholderItem(
        val id: String,
        val content: String,
        val resumo : String,
        val details: String,
        val imageRes: Int,
        val avaliacao: Double,
        val galeria: List<Int>
    ) {
        override fun toString(): String = content
    }
}