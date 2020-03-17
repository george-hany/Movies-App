package com.example.android.movieapp.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.android.movieapp.Adapter.MoviesAdapter
import com.example.android.movieapp.Models.Movie
import com.example.android.movieapp.R
import com.example.android.movieapp.Class.SaveSharedPreference
import com.example.android.movieapp.Interface.Communicator
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

const val MOVIES_URL = "http://api.themoviedb.org/3/movie/popular?api_key=777660159186d81259c9dcfa910ad0f1&page="

class MainActivity : AppCompatActivity() ,Communicator {
    override fun sendData(movie: Movie) {
        val i = Intent(baseContext, DetailsActivity::class.java)
        i.putExtra("title", movie.title)
        i.putExtra("rate", movie.rate)
        i.putExtra("date", movie.date)
        i.putExtra("poster", movie.imagePath)
        i.putExtra("overview", movie.overView)
        i.putExtra("id", movie.id)
        i.putExtra("cover", movie.backdrop_path)
        startActivity(i)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val actionBar=supportActionBar
        actionBar!!.title="Movies"

        getDataFromAPI()
    }

    private fun partItemClicked(movie : Movie) {
        //Toast.makeText(this, "Clicked: ${partItem.itemName}", Toast.LENGTH_LONG).show()

        // Launch second activity, pass part ID as string parameter
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("id", movie.id)
        intent.putExtra("title",movie.title)
        intent.putExtra("date",movie.date)
        intent.putExtra("rate",movie.rate)
        intent.putExtra("adult",movie.adult)
        intent.putExtra("overView",movie.overView)
        intent.putExtra("imagePath",movie.imagePath)
        intent.putExtra("backdrop_path",movie.backdrop_path)
        startActivity(intent)
    }

    private fun getDataFromAPI() {
        val que = Volley.newRequestQueue(this)
        val req = StringRequest(Request.Method.GET, MOVIES_URL, Response.Listener {

            val moviesAdapter = MoviesAdapter(parsingJson(it), this,{ movie : Movie -> partItemClicked(movie) })
            recycle_movies.layoutManager = LinearLayoutManager(this)
            recycle_movies.adapter = moviesAdapter

        }, Response.ErrorListener {
            Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT)
        })

        que.add(req)

    }

    private fun parsingJson(it: String?): ArrayList<Movie> {
        val movies: ArrayList<Movie> = arrayListOf<Movie>()
        val jsonObject = JSONObject(it)
        val results = jsonObject.getJSONArray("results")
        //(id:String, title:String,imagePath:String,rate:String,date:String,adult:String,overView:String)
        for (index: Int in 0..(results.length() - 1)) {
            val movie = results.getJSONObject(index)
            val newMovie = Movie(
                movie.getString("id"),
                movie.getString("title"),
                movie.getString("poster_path"),
                movie.getString("vote_average"),
                movie.getString("release_date"),
                movie.getString("adult"),
                movie.getString("overview"),
                movie.getString("backdrop_path")
            )
            movies.add(newMovie)
        }

        return movies

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logout -> {
                val pref = SaveSharedPreference(this)
                pref.logOut()
                val intent= Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
                return true
            }
            R.id.favorites->{
                val intent= Intent(this, favoriteActivity::class.java)
                startActivity(intent)
            }
        }
        return false

    }
}
