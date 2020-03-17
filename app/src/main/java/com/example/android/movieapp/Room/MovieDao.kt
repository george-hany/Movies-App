package com.example.android.movieapp.Room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
//import androidx.room.Query
//import java.nio.charset.CodingErrorAction.REPLACE

@Dao
interface MovieDao {

    @Insert(onConflict = REPLACE)
    fun insert(movieTable: MovieTable)

    @Delete
    fun delete(movieTable: MovieTable)

    @Query("select * from MovieTable")
    fun getAllMovies():LiveData<List<MovieTable>>

    @Query("DELETE from MovieTable")
    fun deleteAll()
    //@Query("select * from MovieTable where ")
}