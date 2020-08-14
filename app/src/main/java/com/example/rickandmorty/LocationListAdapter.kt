package com.example.rickandmorty

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.vh_location.view.*

interface ClickListener {
    fun onClickItem(urls: Array<String>)
}

class LocationListAdapter(private val ClickListener: ClickListener) : RecyclerView.Adapter<LocationVH>() {

    private val data : ArrayList<Location> = ArrayList<Location>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationVH {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.vh_location, parent, false)
        return LocationVH(root)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: LocationVH, position: Int) {
        holder.itemView.name.text = data[position].name
        holder.itemView.type.text = "Type: " + data[position].type
        holder.itemView.dimension.text = "Dimension: " + data[position].dimension
        holder.itemView.setOnClickListener {
            ClickListener.onClickItem(data[position].residents)
        }
    }

    fun updateList(newData: Array<Location>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }


}

class LocationVH(root: View) : RecyclerView.ViewHolder(root)