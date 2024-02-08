package com.example.mars_photos

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.mars_photos.viewModel.RetrofitViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import www.sanju.zoomrecyclerlayout.ZoomRecyclerLayout

class MainActivity : AppCompatActivity() {

    private lateinit var btn1:Button
    private lateinit var rc:RecyclerView
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         btn1 = findViewById(R.id.btn1)
         rc = findViewById(R.id.recycler_view)
        if (isNetworkAvailable(this)) {
            searchbtn()
        } else {
            // No internet connection
            Toast.makeText(this@MainActivity, "no internet connection", Toast.LENGTH_LONG).show()
        }


    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onResume() {
        super.onResume()
        if(isNetworkAvailable(this)){

            searchbtn()
            Toast.makeText(this@MainActivity, " connected to internet", Toast.LENGTH_LONG).show()

        } else {
            // No internet connection
            Toast.makeText(this@MainActivity, "no internet connection", Toast.LENGTH_LONG).show()

        }
    }


    fun searchbtn(){

        btn1.setOnClickListener {
            btn1.visibility = View.INVISIBLE
            val font_img = findViewById<ImageView>(R.id.font_img)
            font_img.visibility = View.INVISIBLE
            rc.visibility = View.VISIBLE
            fetchData(rc, "NAVCAM")
            Toast.makeText(this@MainActivity, "Searching...", Toast.LENGTH_LONG).show()
        }
    }

    fun fetchData(rc: RecyclerView, camera: String) {
        val viewModel = ViewModelProvider(this@MainActivity).get(RetrofitViewModel::class.java)
        viewModel.fetchMarsPhotos(camera).observe(this@MainActivity, Observer {

            val photoAdapter = PhotoAdapter(this@MainActivity, it!!.photos)
            rc.adapter = photoAdapter

            val linearLayoutManager = ZoomRecyclerLayout(this)
            linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
            linearLayoutManager.reverseLayout = true
            linearLayoutManager.stackFromEnd = true
            rc.layoutManager =
                linearLayoutManager // Add your recycler view to this ZoomRecycler layout

            linearLayoutManager.orientation = LinearLayoutManager.VERTICAL

            val snapHelper = LinearSnapHelper()
            snapHelper.attachToRecyclerView(rc) // Add your recycler view here
            rc.isNestedScrollingEnabled = false

        })
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork
        val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
        return networkCapabilities != null &&
                (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                        networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))
    }
}

