package com.example.android.movieapp.Adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.movieapp.Models.Movie
import com.example.android.movieapp.Models.Review
import com.example.android.movieapp.R
import kotlinx.android.synthetic.main.review_item.view.*

class ReviewAdapter (reviews:ArrayList<Review>,context:Context) :RecyclerView.Adapter<ReviewAdapter.Holder>() {
    val reviews:ArrayList<Review> = reviews
    val context:Context = context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.review_item,parent,false)
        val h = Holder(v)
        return h

    }

    override fun getItemCount(): Int =reviews.size

    override fun onBindViewHolder(holder: Holder, index: Int) {
        holder.name.setText(reviews.get(index).name)
        holder.review.setText((reviews.get(index).review))
    }

    inner class Holder (view:View):RecyclerView.ViewHolder(view){
        val name=view.name
        val review=view.review

    }
}