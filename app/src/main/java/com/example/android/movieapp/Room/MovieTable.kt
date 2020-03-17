package com.example.android.movieapp.Room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
public class MovieTable (
    @PrimaryKey
    val movieid:String,
    val title:String,
    val imagePath:String,
    val rate:String,
    val date:String,
    val adult:String,
    val overView:String,
    val backdrop_path:String){

//    var id:String=id
//    var title=title
//    var imagePath=imagePath
//    var rate=rate
//    var date=date
//    var adult=adult
//    var overView=overView
//    var backdrop_path=backdrop_path
}