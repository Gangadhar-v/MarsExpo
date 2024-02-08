package com.example.mars_photos.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mars_photos.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitRepository {

    var data = MutableLiveData<Photos?>()

    fun fetchMarsPhotos(camera:String): LiveData<Photos?> {

        RetrofitInstance.retrofitService.fetchMarsPhotos(1000,
            1,
            "edtV8TPWtW7ldAXce4cD8QCbfYpcA7wpiQif1dIC",
            camera).enqueue(object :Callback<Photos>{
            override fun onResponse(
                call: Call<Photos>,
                response: Response<Photos>
            ) {
                val responseBody = response.body()
                if (responseBody != null) {
                    data.value = responseBody

                }
            }

            override fun onFailure(call: Call<Photos>, t: Throwable) {
               data.value = null

            }

        })

        return data

    }

}