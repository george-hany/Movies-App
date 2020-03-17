package com.example.android.movieapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android.movieapp.Models.Movie
import com.example.android.movieapp.R
import com.google.android.youtube.player.YouTubePlayerSupportFragment
import kotlinx.android.synthetic.main.video_item.view.*

class VideosAdapter (videos:ArrayList<String>,context:Context,val clickListener: (String) -> Unit) :RecyclerView.Adapter<VideosAdapter.Holder>() {
    val videos=videos
    val context = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.video_item,parent,false)
        val h = Holder(v)
        return h
    }

    override fun getItemCount(): Int =videos.size

    override fun onBindViewHolder(holder: Holder, index: Int) {
        Glide.with(context).load("http://img.youtube.com/vi/${videos.get(index)}/1.jpg").into(holder.video_image)
        Glide.with(context).load(R.drawable.youtube_icon).into(holder.icon_image)

        holder.bind(videos[index], clickListener)
    }

    class Holder(view: View):RecyclerView.ViewHolder(view) {

        val video_image=view.video_image
        val icon_image =view.icon_image

        fun bind(part: String, clickListener: (String) -> Unit) {

            itemView.setOnClickListener { clickListener(part)


            }
        }

    }
}