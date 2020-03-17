package com.example.android.movieapp.Interface

import com.example.android.movieapp.Models.Movie

interface Communicator {
    fun sendData(movie:Movie)
}