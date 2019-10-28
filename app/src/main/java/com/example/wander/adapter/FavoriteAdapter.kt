package com.example.wander.adapter

import android.service.autofill.TextValueSanitizer
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wander.R
import com.example.wander.model.Favorite

class FavoriteAdapter(private val  favorites: List<Favorite>) : RecyclerView.Adapter<FavoriteAdapter.CustomViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.favorite_item, parent, false))
    }

    override fun getItemCount(): Int {
        return favorites.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.apply {
            latText.text = favorites[position].Latitude.toString()
            longText.text = favorites[position].Longitude.toString()
        }
    }

    class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val latText: TextView = view.findViewById(R.id.text_view_lat)
        val longText: TextView = view.findViewById(R.id.text_view_long)
    }
}