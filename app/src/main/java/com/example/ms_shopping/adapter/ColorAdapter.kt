package com.example.ms_shopping.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ms_shopping.Model.BrandModel
import com.example.ms_shopping.R
import com.example.ms_shopping.databinding.ViewholderBrandBinding
import com.example.ms_shopping.databinding.ViewholderColorBinding

class ColorAdapter(
    val items: MutableList<String>
):RecyclerView.Adapter<ColorAdapter.ViewHolder>(){


    private var selectedPosition = -1
    private var lastSelectedPosition = -1
    private lateinit var context: Context

    class ViewHolder(val binding:ViewholderColorBinding):
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val binding = ViewholderColorBinding.inflate(LayoutInflater.from(context),parent, false)
        return  ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ColorAdapter.ViewHolder, @SuppressLint("RecyclerView") position: Int) {


        Glide.with(holder.itemView.context)
            .load(items[position])
            .into(holder.binding.pic1)

        holder.binding.root.setOnClickListener{
            lastSelectedPosition = selectedPosition
            selectedPosition = position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)

        }

        if(selectedPosition == position) {
            holder.binding.colorLayout.setBackgroundResource(R.drawable.grey_bg_selected)
        }else{
            holder.binding.colorLayout.setBackgroundResource(R.drawable.grey_bg)
        }
    }


}