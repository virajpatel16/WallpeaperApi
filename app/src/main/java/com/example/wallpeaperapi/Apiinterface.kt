package com.example.wallpeaperapi

import com.example.wallpaperapi.WallpaperModel
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface Apiinterface {

    @GET("search")
    fun getdata(
 @Query("query")query:String,
 @Query("page")page:Int,
 @Header("Authorization")auth:String
    ):retrofit2.Call<WallpaperModel>
}