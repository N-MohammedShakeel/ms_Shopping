package com.example.ms_shopping.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.request.RequestOptions
import com.example.ms_shopping.Model.SliderModel
import com.example.ms_shopping.R

class SliderAdapter(
    private val viewPager2: ViewPager2
) : ListAdapter<SliderModel, SliderAdapter.SliderViewHolder>(SliderModelDiffCallback()) {

    private lateinit var context: Context
    private val runnable = Runnable {
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        context = parent.context
        val view = LayoutInflater.from(parent.context).inflate(R.layout.slider_item_container, parent, false)
        return SliderViewHolder(view)
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        val sliderItem = getItem(position) // Retrieve the item at the given position
        holder.setImage(sliderItem, context) // Pass the retrieved item to setImage method
        if (position == currentList.lastIndex && viewPager2.currentItem == currentList.lastIndex) {
            viewPager2.post(runnable)
        }
    }

    class SliderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.imageslide)

        fun setImage(sliderItem: SliderModel, context: Context) {
            val requestOptions = RequestOptions().transform(CenterInside())
            Glide.with(itemView.context)
                .load(sliderItem.url)
                .apply(requestOptions)
                .into(imageView)
        }
    }

    class SliderModelDiffCallback : DiffUtil.ItemCallback<SliderModel>() {
        override fun areItemsTheSame(oldItem: SliderModel, newItem: SliderModel): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: SliderModel, newItem: SliderModel): Boolean {
            return oldItem == newItem
        }
    }
}
