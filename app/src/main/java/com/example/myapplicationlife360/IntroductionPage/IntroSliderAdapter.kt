package com.example.myapplicationlife360.IntroductionPage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationlife360.R

class IntroSliderAdapter(val introSlide: List<IntroSlide>):RecyclerView.Adapter<IntroSliderAdapter.IntroSlideViewAdapter>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntroSlideViewAdapter {
       return  IntroSlideViewAdapter(
           LayoutInflater.from(parent.context).inflate(
               R.layout.slider_item_container,
               parent,
               false
           )
       )
    }

    override fun onBindViewHolder(holder: IntroSlideViewAdapter, position: Int) {
        holder.bind(introSlide[position])
    }

    override fun getItemCount(): Int {
       return introSlide.size
    }

    inner class IntroSlideViewAdapter(view: View):RecyclerView.ViewHolder(view){

        val textDescription=view.findViewById<TextView>(R.id.textDescription)

        fun bind(introSlide: IntroSlide){

            textDescription.text=introSlide.description
        }
    }

}