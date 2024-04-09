package com.example.ms_shopping.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ms_shopping.Helper.ManagmentCart
import com.example.ms_shopping.Model.ItemsModel
import com.example.ms_shopping.Model.SliderModel
import com.example.ms_shopping.adapter.ColorAdapter
import com.example.ms_shopping.adapter.SizeAdapter
import com.example.ms_shopping.adapter.SliderAdapter
import com.example.ms_shopping.databinding.ActivityDetailBinding

class DetailActivity : BaseActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var item :ItemsModel
    private var numberOder = 1
    private lateinit var managmentCart: ManagmentCart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managmentCart = ManagmentCart(this)
        getBundle()
        banner()
        initLists()

    }

    private fun initLists() {
        // Size Adapter
        val sizeList = ArrayList<String>()
        for (size in item.size) {
            sizeList.add(size.toString())
        }
        binding.sizeList.adapter = SizeAdapter(sizeList)
        binding.sizeList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        // Color Adapter
        val colorList = ArrayList<String>()
        for (imageUrl in item.picUrl) {
            colorList.add(imageUrl)
        }

        binding.colorList.adapter = ColorAdapter(colorList)
        binding.colorList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }



    private fun banner() {
        // Prepare the list of slider items
        val sliderItems = ArrayList<SliderModel>()
        for (imageUrl in item.picUrl) {
            sliderItems.add(SliderModel(imageUrl))
        }

        // Set up the adapter with the ViewPager2
        val adapter = SliderAdapter(binding.slider)
        adapter.submitList(sliderItems) // Submit the list of slider items to the adapter
        binding.slider.adapter = adapter

        // Configure ViewPager2 settings
        binding.slider.clipToPadding = true
        binding.slider.clipChildren = true
        binding.slider.offscreenPageLimit = 3

        // Show dots indicator if there are multiple items
        if (sliderItems.size > 1) {
            binding.dotsIndicator.visibility = View.VISIBLE
            binding.dotsIndicator.attachTo(binding.slider)
        }
    }


    @SuppressLint("SetTextI18n")
    private fun getBundle() {
        item = intent.getParcelableExtra("object")!!
        binding.titleTxt.text = item.title
        binding.descriptionTxt.text = item.description
        binding.priceTxt.text = "$" + item.price
        binding.ratingTxt.text = "${item.rating} Rating"

        binding.addToCartBtn.setOnClickListener {
            item.numberInCart = numberOder
            managmentCart.insertFood(item)
        }

        binding.backbtn.setOnClickListener { finish() }
        binding.cartBtn.setOnClickListener {
            startActivity(Intent(this@DetailActivity ,CartActivity::class.java))
        }

    }
}