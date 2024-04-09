package com.example.ms_shopping.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.ms_shopping.Model.SliderModel
import com.example.ms_shopping.R
import com.example.ms_shopping.adapter.SliderAdapter
import com.example.ms_shopping.ViewModel.MainViewModel
import com.example.ms_shopping.adapter.BrandAdapter
import com.example.ms_shopping.adapter.PopularAdapter
import com.example.ms_shopping.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val viewModel = MainViewModel()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startProgressBarAnimation()
        initBanner()
        initBrand()
        initPopular()
        initBottomMenu()
    }

    private fun initBottomMenu() {
        binding.cartbtn.setOnClickListener{
            startActivity(Intent(this@MainActivity,CartActivity::class.java))
        }
    }

    private fun startProgressBarAnimation() {
        val rotation = AnimationUtils.loadAnimation(this, R.anim.rotate_animation)
        binding.progressBarBanner.startAnimation(rotation)
        binding.progressBarBrand.startAnimation(rotation)
        binding.ProgressBarPopular.startAnimation(rotation)

    }

    private fun initBanner() {
        binding.progressBarBanner.visibility = View.VISIBLE
        viewModel.banners.observe(this, Observer { items ->
            banners(items)
            binding.progressBarBanner.clearAnimation()
            binding.progressBarBanner.visibility = View.GONE
        })
        viewModel.LoadBanners()
    }

    private fun banners(image: List<SliderModel>) {
        val adapter = SliderAdapter(binding.viewPagerSlider)
        adapter.submitList(image)
        binding.viewPagerSlider.adapter = adapter
        binding.viewPagerSlider.clipToPadding = false
        binding.viewPagerSlider.clipChildren = false
        binding.viewPagerSlider.offscreenPageLimit = 3
        binding.viewPagerSlider.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val compositePageTransformer = CompositePageTransformer().apply {
            addTransformer(MarginPageTransformer(40))
        }
        binding.viewPagerSlider.setPageTransformer(compositePageTransformer)

        if (image.size > 1) {
            binding.dotsIndicator.visibility = View.VISIBLE
            binding.dotsIndicator.attachTo(binding.viewPagerSlider)
        }
    }

    private fun initBrand() {
        binding.progressBarBrand.visibility = View.VISIBLE
        viewModel.brands.observe(this, Observer {
            binding.viewBrand.layoutManager = LinearLayoutManager(this@MainActivity , LinearLayoutManager.HORIZONTAL , false)
            binding.viewBrand.adapter = BrandAdapter(it)

            binding.progressBarBrand.clearAnimation()
            binding.progressBarBrand.visibility = View.GONE
        })
        viewModel.loadBrand()
    }

    private fun initPopular() {
        binding.ProgressBarPopular.visibility = View.VISIBLE
        viewModel.popular.observe(this, Observer {
            binding.viewpopular.layoutManager = GridLayoutManager(this@MainActivity , 2)
            binding.viewpopular.adapter = PopularAdapter(it)

            binding.ProgressBarPopular.clearAnimation()
            binding.ProgressBarPopular.visibility = View.GONE
        })
        viewModel.LoadPopular()
    }

}
