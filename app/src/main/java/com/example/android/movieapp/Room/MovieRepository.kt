package com.example.android.roomapp

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.android.movieapp.Room.MovieDao
import com.example.android.movieapp.Room.MovieTable

class MovieRepository (private val wordDao:MovieDao){
    val allWords:  LiveData<List<MovieTable>>  = wordDao.getAllMovies()

    @WorkerThread
    fun insert(movie: MovieTable) {
        wordDao.insert(movie)
    }
    fun delete(movie: MovieTable) {
        wordDao.delete(movie)
    }
//    fun update(movie: MovieTable) {
//        wordDao.update(movie)
//    }
}