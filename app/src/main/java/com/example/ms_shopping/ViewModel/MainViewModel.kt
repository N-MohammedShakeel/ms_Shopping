package com.example.ms_shopping.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ms_shopping.Model.BrandModel
import com.example.ms_shopping.Model.ItemsModel
import com.example.ms_shopping.Model.SliderModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainViewModel(): ViewModel() {

    private val firebaseDatabase = FirebaseDatabase.getInstance()

    private val _banner = MutableLiveData<List<SliderModel>>()
    val banners: LiveData<List<SliderModel>> = _banner

    private val _brand = MutableLiveData<MutableList<BrandModel>>()
    val brands : LiveData<MutableList<BrandModel>> = _brand

    private val _popular = MutableLiveData<MutableList<ItemsModel>>()
    val popular : LiveData<MutableList<ItemsModel>> = _popular




    fun LoadBanners() {

        val Ref = firebaseDatabase.getReference("Banner")
        Ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<SliderModel>()
                for (childSnapshot in snapshot.children) {
                    val list = childSnapshot.getValue(SliderModel::class.java)
                    if (list != null) {
                        lists.add(list)
                    }
                }
                _banner.value = lists

            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    fun loadBrand(){
        val Ref = firebaseDatabase.getReference("Category")
        Ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<BrandModel>()
                for (childSnapshot in snapshot.children) {
                    val title = childSnapshot.child("title").getValue(String::class.java) ?: ""
                    val id = childSnapshot.child("brandId").getValue(Int::class.java) ?: 0
                    val pidUrl = childSnapshot.child("picUrl").getValue(String::class.java) ?: ""

                    val brandModel = BrandModel(title, id, pidUrl)
                    lists.add(brandModel)
                }
                _brand.value = lists
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle onCancelled
            }
        })
    }

    fun LoadPopular() {

        val Ref = firebaseDatabase.getReference("Items")
        Ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<ItemsModel>()
                for (childSnapshot in snapshot.children) {
                    val list = childSnapshot.getValue(ItemsModel::class.java)
                    if (list != null) {
                        lists.add(list)
                    }
                }
                _popular.value = lists

            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }


}

