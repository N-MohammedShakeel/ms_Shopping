package com.example.ms_shopping.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.ms_shopping.Helper.ChangeNumberItemsListener
import com.example.ms_shopping.Helper.ManagmentCart
import com.example.ms_shopping.Model.ItemsModel
import com.example.ms_shopping.databinding.ViewholderCartBinding

class CartAdapter (
    private val ListItemseletected: ArrayList<ItemsModel>,
    context: Context,
    var changeNumberItemsListener: ChangeNumberItemsListener? = null
) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    class ViewHolder(val binding: ViewholderCartBinding): RecyclerView.ViewHolder(binding.root) {

    }
    private val managementCart = ManagmentCart(context)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter. ViewHolder {

        val binding= ViewholderCartBinding.inflate(LayoutInflater.from(parent.context), parent,  false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = ListItemseletected.size

    override fun onBindViewHolder(holder: CartAdapter.ViewHolder, position: Int) {

        val item = ListItemseletected[position]

        holder.binding.titletxt.text = item.title
        holder.binding.feeeachitem.text = "$${item.price}"
        holder.binding.totaleachitem.text = "$${Math.round(item.numberInCart * item.price)}"
        holder.binding.number.text = item.numberInCart.toString()


        Glide.with(holder.itemView.context)
            .load(item.picUrl[0])
            .apply(RequestOptions().transform(CenterCrop()))
            .into(holder.binding.pic)

        holder.binding.plus.setOnClickListener {

            managementCart.plusItem(ListItemseletected, position, object: ChangeNumberItemsListener {


                    override fun onChanged() {
                        notifyDataSetChanged()
                        changeNumberItemsListener?.onChanged()

                    }
            })
        }

        holder.binding.minuscartbtn.setOnClickListener {

            managementCart.minusItem(ListItemseletected, position,

                object : ChangeNumberItemsListener {
                    override fun onChanged() {
                        notifyDataSetChanged()
                        changeNumberItemsListener?.onChanged()
                    }
                })
        }



    }
}