package com.example.themovieapp

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.themovieapp.databinding.ItemMovieBinding
import com.example.themovieapp.placeholder.PlaceholderContent.PlaceholderItem


interface MovieItemListener{
    fun onItemSelected(position: Int)
}
class MyItemRecyclerViewAdapter(
    private val values: List<PlaceholderItem>,

    private val listener: MovieItemListener

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

        holder.bindingItem(item)

        // Sinopse de até 140 caracteres (que está no campo details do Placeholder)
       // holder.sinopseView.text = item.resumo

        // Foto filme
        holder.fotoFilme.setImageResource(item.imageRes)

        holder.view.setOnClickListener {
            listener.onItemSelected(position)
        }
    }


    override fun getItemCount(): Int = values.size

    inner class ViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
       val view = binding.root

        val fotoFilme : ImageView = binding.imageView
        fun bindingItem(item: PlaceholderItem){
            binding.movieItem = item
            binding.executePendingBindings()

        }
    }
}