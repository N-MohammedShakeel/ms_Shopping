package com.example.ms_shopping.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.ms_shopping.Model.ItemsModel
import com.example.ms_shopping.activity.DetailActivity
import com.example.ms_shopping.databinding.ViewholderRecommendedBinding

class PopularAdapter(val items: MutableList<ItemsModel>): RecyclerView.Adapter<PopularAdapter.ViewHolder>() {

    private var context: Context? = null
    class ViewHolder(val binding: ViewholderRecommendedBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularAdapter.ViewHolder {

        context = parent.context
        val binding = ViewholderRecommendedBinding.inflate(LayoutInflater.from(context) , parent ,false)
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: PopularAdapter.ViewHolder, position: Int) {
        val item = items[position]

        holder.binding.titleTxt.text = item.title
        holder.binding.priceTxt.text = "$" + item.price.toString() // Corrected line to display the price
        holder.binding.ratingTxt.text = item.rating.toString()

        val requestOptions = RequestOptions().transform(CenterCrop())

        Glide.with(holder.itemView.context)
            .load(item.picUrl[0])
            .apply(requestOptions)
            .into(holder.binding.pic)


        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context,DetailActivity::class.java)
            intent.putExtra("object",items[position])
            holder.itemView.context.startActivity(intent)
        }
    }


    override fun getItemCount(): Int = items.size
}