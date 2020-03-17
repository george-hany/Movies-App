package com.example.android.movieapp.Activity

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.android.movieapp.Class.SaveSharedPreference
import com.example.android.movieapp.Fragment.DetailsFragment
import com.example.android.movieapp.Fragment.ReviewsFragment
import com.example.android.movieapp.Fragment.VideosFragment
import com.example.android.movieapp.R
import com.example.android.movieapp.Room.MovieDB
import com.example.android.movieapp.Room.MovieTable
import com.example.android.movieapp.ui.main.SectionsPagerAdapter
import com.example.android.roomapp.MovieRepository
import com.example.android.roomapp.MovieViewModel
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.activity_details.view.*
import kotlinx.coroutines.awaitAll
import java.util.ArrayList

class DetailsActivity : AppCompatActivity() {
    lateinit var favorites: List<MovieTable>
    lateinit var movieViewModel:MovieViewModel
    var isFavorite = false
    private lateinit var id: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)
        movieViewModel.allMovies.observe(this, Observer { movies ->
            // Update the cached copy of the words in the adapter.
            movies?.let {
                favorites=it
                checkIsFavorite()

            }
        })

        val actionBar = supportActionBar
        //actionBar!!.title="Details"
        //actionBar.setDisplayHomeAsUpEnabled(true)
        id = intent.getStringExtra("id")
        val title = intent.getStringExtra("title")
        val date = intent.getStringExtra("date")
        val rate = intent.getStringExtra("rate")
        val adult = intent.getStringExtra("adult")
        val overView = intent.getStringExtra("overView")
        val imagePath = intent.getStringExtra("imagePath")
        val backdrop_path = intent.getStringExtra("backdrop_path")



//        if (checkIsFavorite())
//            appBar.favorite_star.setImageResource(R.drawable.ic_star_gold)
//        else
//            appBar.favorite_star.setImageResource(R.drawable.ic_star_white)


        appBar.title.setText("Details")
        appBar.favorite_star.setOnClickListener(View.OnClickListener {
            if (checkIsFavorite()) {
                var movie = MovieTable(id, title, imagePath, rate, date, adult, overView, backdrop_path)
                //val word = Word(it.getStringExtra(NewWordActivity.EXTRA_REPLY))
                movieViewModel.delete(movie)
                appBar.favorite_star.setImageResource(R.drawable.ic_star_white)
            } else {
                var movie = MovieTable(id, title, imagePath, rate, date, adult, overView, backdrop_path)
                //val word = Word(it.getStringExtra(NewWordActivity.EXTRA_REPLY))
                movieViewModel.insert(movie)
                appBar.favorite_star.setImageResource(R.drawable.ic_star_gold)
            }


        })


        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        val viewPagerAdapter = ViewPagerAdapter(getSupportFragmentManager())
        viewPagerAdapter.addFragment(DetailsFragment(), "Details")
        viewPagerAdapter.addFragment(ReviewsFragment(), "Reviews")
        viewPagerAdapter.addFragment(VideosFragment(), "Videos")

        viewPager.adapter = viewPagerAdapter
        tabs.setupWithViewPager(viewPager)
    }

//    private fun makeFavorites(it: List<MovieTable>) {
//            favorites=it
//    }

    fun checkIsFavorite(): Boolean {

        //Toast.makeText(this,"in fun "+favorites.size+id,Toast.LENGTH_LONG).show()
        Log.e("idddd ", id)
        for (index: Int in 0..(favorites.size - 1)) {
            Log.e("idddd $index ", favorites.get(index).movieid)
            if (favorites.get(index).movieid == id) {
                appBar.favorite_star.setImageResource(R.drawable.ic_star_gold)
                isFavorite = true
                return true
            }
        }
        appBar.favorite_star.setImageResource(R.drawable.ic_star_white)

        return false
    }


    internal inner class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
        private val fragments: ArrayList<Fragment>
        private val titles: ArrayList<String>

        init {
            this.fragments = ArrayList()
            this.titles = ArrayList()
        }

        override fun getItem(i: Int): Fragment {
            val intent = getIntent()

            when (i) {
                0 -> {
                    val detailsFragment = DetailsFragment()
                    val bundle = Bundle()
                    bundle.putString("title", intent.extras?.getString("title"))
                    bundle.putString("date", intent.getStringExtra("date"))
                    bundle.putString("rate", intent.getStringExtra("rate"))
                    bundle.putString("adult", intent.getStringExtra("adult"))
                    bundle.putString("overView", intent.getStringExtra("overView"))
                    bundle.putString("imagePath", intent.getStringExtra("imagePath"))
                    bundle.putString("backdrop_path", intent.extras?.getString("backdrop_path"))
                    detailsFragment.arguments = bundle
                    return detailsFragment
                }
                1 -> {
                    val reviewsFragment = ReviewsFragment()
                    val bundle = Bundle()
                    bundle.putString("id", intent.getStringExtra("id"))
                    reviewsFragment.arguments = bundle
                    return reviewsFragment
                }
                2 -> {
                    val videosFragment = VideosFragment()
                    val bundle = Bundle()
                    bundle.putString("id", intent.getStringExtra("id"))
                    videosFragment.arguments = bundle
                    return videosFragment
                }
                else -> {
                    return fragments[i]
                }

            }

        }

        override fun getCount(): Int {
            return fragments.size
        }

        fun addFragment(f: Fragment, t: String) {
            fragments.add(f)
            titles.add(t)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return titles[position]
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
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
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
                return true
            }
        }
        return false

    }


}