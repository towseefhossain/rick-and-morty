package com.example.rickandmorty

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.vh_character.view.*


class CharacterListAdapter : RecyclerView.Adapter<CharacterVH>() {

    private val data : ArrayList<CharacterResult> = ArrayList<CharacterResult>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterVH {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.vh_character, parent, false)
        return CharacterVH(root)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: CharacterVH, position: Int) {
        holder.itemView.name.text = data[position].name
        holder.itemView.status.text = data[position].status
        holder.itemView.species.text = data[position].species
        holder.itemView.type.text = data[position].type
        holder.itemView.gender.text = data[position].gender
        holder.itemView.origin.text = data[position].origin.name
        holder.itemView.location.text = data[position].location.name
        Glide.with(holder.itemView.context).load(data[position].image).into(holder.itemView.image)
    }

    fun updateList(newData: Array<CharacterResult>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }


}

class CharacterVH(root: View) : RecyclerView.ViewHolder(root)