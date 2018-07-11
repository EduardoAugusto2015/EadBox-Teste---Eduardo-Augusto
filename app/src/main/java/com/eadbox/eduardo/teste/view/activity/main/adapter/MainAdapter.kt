package com.eadbox.eduardo.teste.view.activity.main.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.eadbox.eduardo.teste.R
import com.eadbox.eduardo.teste.model.entity.Courses
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso

class MainAdapter(private val coursesList: List<Courses>) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: MainAdapter.ViewHolder, position: Int) {

        val coursesList = coursesList[position]

        Picasso.with(holder.itemView.context)
                .load(coursesList.logo_url)
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .resize(80, 80)
                .into(holder.logo_url)

        holder.txt_title.text = coursesList.title

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.ViewHolder {
        return MainAdapter.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_courses, parent, false))
    }

    override fun getItemCount(): Int {
        return coursesList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var logo_url = itemView.findViewById<ImageView>(R.id.logo_url)
        var txt_title = itemView.findViewById<TextView>(R.id.txt_title)

    }

}