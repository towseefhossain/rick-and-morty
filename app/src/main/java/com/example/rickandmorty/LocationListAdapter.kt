package com.example.rickandmorty

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LocationListAdapter : RecyclerView.Adapter<LocationVH>() {

    private val data = arrayListOf("hi", "rick", "morty")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationVH {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.vh_location, parent, false)
        return LocationVH(root)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: LocationVH, position: Int) {
        (holder.itemView as? TextView)?.text = data[position]
    }

}

class LocationVH(root: View) : RecyclerView.ViewHolder(root)