package com.example.mars_photos.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mars_photos.data.Photos
import com.example.mars_photos.data.RetrofitRepository

class RetrofitViewModel:ViewModel() {

    val repo = RetrofitRepository()


    fun fetchMarsPhotos(camera:String): LiveData<Photos?> {

        return repo.fetchMarsPhotos(camera)
    }
}