package com.example.themovieapp.movieHome

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.example.themovieapp.databinding.ItemMovieBinding
import com.example.themovieapp.data.Movie


interface MovieItemListener{
    fun onItemSelected(position: Int)
}
class MyItemRecyclerViewAdapter(

    private val listener: MovieItemListener

) : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>() {

    private var values: List<Movie> = ArrayList()

    fun updateData(movieList: List <Movie>){
        values = movieList
        notifyDataSetChanged()
    }

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

        holder.view.setOnClickListener {
            listener.onItemSelected(position)
        }
    }


    override fun getItemCount(): Int = values.size

    inner class ViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
       val view = binding.root

        fun bindingItem(item: Movie){
            binding.movieItem = item
            binding.executePendingBindings()

        }
    }
}