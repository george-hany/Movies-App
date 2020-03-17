package com.example.android.movieapp.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.movieapp.Adapter.FavoriteMoviesAdapter
import com.example.android.movieapp.R
import com.example.android.movieapp.Room.MovieTable
import com.example.android.roomapp.MovieViewModel
import kotlinx.android.synthetic.main.activity_favorite.*

class favoriteActivity : AppCompatActivity() {
    private lateinit var movieViewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
        val actionBar=supportActionBar
        actionBar!!.title="Favorite Movies"
        actionBar.setDisplayHomeAsUpEnabled(true)
        val adapter=FavoriteMoviesAdapter(this,{ c:MovieTable->partItemClicked(c)})

        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)
        movieViewModel.allMovies.observe(this, Observer { movies ->
            // Update the cached copy of the words in the adapter.
            movies?.let { adapter.setWords(it) }
        })
        recycle_movies.layoutManager= LinearLayoutManager(this)
        recycle_movies.adapter=adapter

    }

    private fun partItemClicked(movie : MovieTable) {
        //Toast.makeText(this, "Clicked: ${partItem.itemName}", Toast.LENGTH_LONG).show()

        // Launch second activity, pass part ID as string parameter
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("id", movie.movieid)
        intent.putExtra("title",movie.title)
        intent.putExtra("date",movie.date)
        intent.putExtra("rate",movie.rate)
        intent.putExtra("adult",movie.adult)
        intent.putExtra("overView",movie.overView)
        intent.putExtra("imagePath",movie.imagePath)
        intent.putExtra("backdrop_path",movie.backdrop_path)
        startActivity(intent)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
