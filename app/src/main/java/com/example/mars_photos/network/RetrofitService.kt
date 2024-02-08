package com.example.mars_photos.network


import androidx.lifecycle.LiveData
import com.example.mars_photos.data.Photos
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("mars-photos/api/v1/rovers/curiosity/photos")
    fun fetchMarsPhotos(@Query("sol") sol:Int,@Query("page") page:Int, @Query("api_key") apiKey: String,
                        @Query("camera") camera: String): Call<Photos>
}