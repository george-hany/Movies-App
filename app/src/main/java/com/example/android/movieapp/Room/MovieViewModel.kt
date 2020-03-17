package com.example.android.roomapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.android.movieapp.Room.MovieDB
import com.example.android.movieapp.Room.MovieTable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: MovieRepository
    val allMovies: LiveData<List<MovieTable>>

    init {
        val wordsDao = MovieDB.getDatabase(application,viewModelScope).movieDao()
        repository = MovieRepository(wordsDao)
        allMovies = repository.allWords
    }

    fun insert(movie: MovieTable) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(movie)
    }
    fun delete(movie: MovieTable) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(movie)
    }
//    fun update(movie: MovieTable) = viewModelScope.launch(Dispatchers.IO) {
//        repository.update(movie)
//    }
}