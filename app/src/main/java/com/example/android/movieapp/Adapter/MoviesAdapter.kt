package com.example.android.movieapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android.movieapp.Interface.Communicator
import com.example.android.movieapp.Models.Movie
import com.example.android.movieapp.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_item.view.*

class MoviesAdapter(movies:ArrayList<Movie>,con:Context,val clickListener: (Movie) -> Unit) : RecyclerView.Adapter<MoviesAdapter.Holder>(){
    val movies:ArrayList<Movie> = movies
    val context:Context = con

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.movie_item,parent,false)
        val h = Holder(v)
        return h

    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: Holder, index: Int) {
        holder.title.setText(movies.get(index).title)
        holder.rate.setText(movies.get(index).rate)
        holder.date.setText(movies.get(index).date)
        holder.overView.setText(movies.get(index).overView)
        holder.adult.setText(movies.get(index).adult)
        //Picasso.with(context).load("http://image.tmdb.org/t/p/w185/${movies.get(index).imagePath}").placeholder(R.drawable.placeholder).into(holder.movie_image)
        Glide.with(context).load("http://image.tmdb.org/t/p/w185/${movies.get(index).imagePath}").into(holder.movie_image);

        holder.itemView.setOnClickListener(View.OnClickListener {

        })

        holder.bind(movies[index], clickListener)
    }


    class Holder(view:View) :RecyclerView.ViewHolder(view) {
        val v=view
        val title=view.movie_title
        val movie_image=view.movie_image
        val rate=view.rate_txt
        val date=view.date_txt
        val adult=view.adult_txt
        val overView=view.overView_txt

        fun bind(part: Movie, clickListener: (Movie) -> Unit) {

            itemView.setOnClickListener { clickListener(part)}
        }





    }

}