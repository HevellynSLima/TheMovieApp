package com.example.themovieapp

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.themovieapp.databinding.ItemMovieBinding
import com.example.themovieapp.placeholder.PlaceholderContent.PlaceholderItem

class MyItemRecyclerViewAdapter(
    private val values: List<PlaceholderItem>,
    private val onItemClick: (Int) -> Unit
) : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]

        // Nome do filme
        holder.idView.text = item.content

        // Sinopse de até 140 caracteres (que está no campo details do Placeholder)
        holder.sinopseView.text = item.resumo

        // Foto filme
        holder.fotoFilme.setImageResource(item.imageRes)

        holder.itemView.setOnClickListener {
            onItemClick(position)
        }
    }


    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.titulo
        val sinopseView: TextView = binding.sinopse
        val fotoFilme : ImageView = binding.imageView
    }
}