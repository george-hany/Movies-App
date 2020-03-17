package com.example.android.movieapp.Fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.textclassifier.TextLinks
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager

import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.android.movieapp.Adapter.MoviesAdapter
import com.example.android.movieapp.Adapter.ReviewAdapter
import com.example.android.movieapp.Models.Review

import com.example.android.movieapp.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_reviews.*
import org.json.JSONObject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */

class ReviewsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v= inflater.inflate(R.layout.fragment_reviews, container, false)

        getDataFromAPI()

        return v
    }

    private fun getDataFromAPI() {
        val bundel=this.arguments
        val REVIWES_URL="http://api.themoviedb.org/3/movie/${bundel?.getString("id")}/reviews?api_key=777660159186d81259c9dcfa910ad0f1"
        val que=Volley.newRequestQueue(context)
        val req =StringRequest(Request.Method.GET,REVIWES_URL,Response.Listener {
            val mReviewAdapter= ReviewAdapter(parsingJson(it),requireContext())
            recycle_review.layoutManager=LinearLayoutManager(context)
            recycle_review.adapter=mReviewAdapter
        },Response.ErrorListener {
            Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT)


        })

        que.add(req)



    }

    private fun parsingJson(it: String?): ArrayList<Review> {
        val reviews:ArrayList<Review> = arrayListOf()
        val jsonObject=JSONObject(it)
        val jsonArray=jsonObject.getJSONArray("results")
        for (index:Int in 0..(jsonArray.length()-1)){
            val  review=jsonArray.getJSONObject(index)
            val newreview=Review(review.getString("author"),review.getString("content"))
            reviews.add(newreview)
        }

        return reviews

    }


}
