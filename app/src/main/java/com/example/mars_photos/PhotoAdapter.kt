package com.example.mars_photos

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.mars_photos.data.Photo

class PhotoAdapter(val context: Context, val photos: List<Photo>):
    Adapter<PhotoAdapter.PhotoViewHolder>() {

    inner class PhotoViewHolder(item: View):ViewHolder(item){
        val image = item.findViewById<ImageView>(R.id.image_view)
        var name = item.findViewById<TextView>(R.id.name)
        var earth_date = item.findViewById<TextView>(R.id.earth_date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val item = LayoutInflater.from(context).inflate(R.layout.item,parent,false)
        return PhotoViewHolder(item)
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {


        val photo = photos.get(position)
        holder.name.text = "Rover:${photo.rover.name}"
        holder.earth_date.text = photo.earth_date

        Glide.with(context)
            .load(photo.img_src)
            .fitCenter()
            .placeholder(R.drawable.img_1)
            .into(holder.image)
    }
}