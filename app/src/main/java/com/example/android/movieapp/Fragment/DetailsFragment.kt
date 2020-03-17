package com.example.android.movieapp.Fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.android.movieapp.R
import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.android.synthetic.main.fragment_details.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class DetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v =inflater.inflate(R.layout.fragment_details, container, false)
        val bundle=this.arguments

        Glide.with(this).load("http://image.tmdb.org/t/p/w185/${bundle?.getString("backdrop_path")}").into(v.background_image)
        Glide.with(this).load("http://image.tmdb.org/t/p/w185/${bundle?.getString("imagePath")}").into(v.poster_image)
        v.title.setText(bundle?.getString("title"))
        v.rate.setText(bundle?.getString("rate"))
        v.date.setText(bundle?.getString("date"))
        v.adult.setText(bundle?.getString("adult"))
        v.overView.setText(bundle?.getString("overView"))


        return v
    }




}
