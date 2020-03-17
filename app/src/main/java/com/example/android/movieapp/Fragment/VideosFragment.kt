package com.example.android.movieapp.Fragment


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.android.movieapp.Adapter.VideosAdapter

import com.example.android.movieapp.R
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerSupportFragment
import kotlinx.android.synthetic.main.fragment_videos.*
import org.json.JSONObject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
const val KEY="AIzaSyAk3F3gHi-8izLDmKzqTdzpyjo2yS_iFLc"
class VideosFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v= inflater.inflate(R.layout.fragment_videos, container, false)
        getDataFromApi()

        return v
    }

    private fun getDataFromApi() {
        val bundel=this.arguments
        val VIDEO_KEY="http://api.themoviedb.org/3/movie/${bundel?.getString("id")}/videos?api_key=777660159186d81259c9dcfa910ad0f1"
        val que=Volley.newRequestQueue(context)
        val req=StringRequest(Request.Method.GET,VIDEO_KEY,Response.Listener {
            val videosAdapter=VideosAdapter(parsingJson(it),requireContext(),{ key : String -> partItemClicked(key) })
            recycle_videos.layoutManager=LinearLayoutManager(context)
            recycle_videos.adapter=videosAdapter

        },Response.ErrorListener {
            Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT)

        })
        que.add(req)
    }
    private fun parsingJson(it: String?): ArrayList<String> {
        val videos:ArrayList<String> = arrayListOf()
        val jsonObject=JSONObject(it)
        val jsonArray=jsonObject.getJSONArray("results")
        for (index:Int in 0..(jsonArray.length()-1)){
            val video=jsonArray.getJSONObject(index)
            videos.add(video.getString("key"))
        }

        return videos

    }

    private fun partItemClicked(movie: Any) {
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("http://www.youtube.com/watch?v=$movie")
        )
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)


    }
}
