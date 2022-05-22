package com.moviles.retofirebase

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moviles.retofirebase.databinding.MoviesBinding
import org.json.JSONArray
import org.json.JSONObject

class MainAdapter (private val movies: JSONArray): RecyclerView.Adapter<MainAdapter.MainHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = MoviesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        if(movies[position].toString()!="null"){
            holder.render(movies[position] as JSONObject)
        }
    }

    override fun getItemCount(): Int = movies.length()

    class MainHolder(val binding: MoviesBinding): RecyclerView.ViewHolder(binding.root){
        fun render(movie: JSONObject){
            binding.tvMovieTitle.setText(movie.getString("title"))
            binding.tvMovieCountry.setText(movie.getString("country"))
            binding.ivPoster.setText(movie.getString("poster"))
            binding.tvMovieGenre.setText(movie.getString("genre"))
            binding.tvMovieType.setText(movie.getString("type"))
            binding.tvMovieYear.setText(movie.getString("year"))
            binding.tvMovieimdbID.setText(movie.getString("imdbID"))
            binding.ivMovieThumbnail.setImageResource(R.drawable.movies)
        }
    }
}