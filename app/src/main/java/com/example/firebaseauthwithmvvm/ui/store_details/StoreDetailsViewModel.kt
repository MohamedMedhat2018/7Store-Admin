package com.example.firebaseauthwithmvvm.ui.store_details

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.firebaseauthwithmvvm.constants.Constants
import com.example.firebaseauthwithmvvm.models.StoreProduct
import com.google.gson.Gson
import com.pixplicity.easyprefs.library.Prefs
import com.squareup.picasso.Picasso


class StoreDetailsViewModel() : ViewModel() {

    var storeProductModel = MutableLiveData<StoreProduct>()

    var getStoreProductModel: LiveData<StoreProduct> = storeProductModel

    var ProductImage = MutableLiveData<String>()

//    fun getAProductImage(): LiveData<String> = ProductImage

    var productName: MutableLiveData<String> = MutableLiveData()

    var productQuantity = MutableLiveData<String>()

    var productCostValue = MutableLiveData<String>()

    var productPrice = MutableLiveData<String>()


    fun data() {
        if (Prefs.contains(Constants.PRODUCT)) {
            lateinit var ProductDetails: StoreProduct
            val gson: Gson = Gson()
            val json = Prefs.getString(Constants.PRODUCT, "")
            ProductDetails = gson.fromJson(json, StoreProduct::class.java)
            Log.e("Data", "Get details " + ProductDetails.product_name)

            getData(ProductDetails)
//            viewModel.getData(ProductDetails)
        }
    }

    fun getData(storeProduct: StoreProduct) {
        storeProductModel.value = storeProduct
        Log.e("data", "great work " + storeProductModel.value!!.product_name)
        loadData()
    }

    fun loadData() {
        Log.e("data", "great work2.1 ")
        if (storeProductModel.value != null) {
            productName.value = storeProductModel.value!!.product_name

            ProductImage.value = storeProductModel.value!!.product_image

            productQuantity.value = storeProductModel.value!!.product_quantity
            productCostValue.value = storeProductModel.value!!.product_material_Cost
            productPrice.value = storeProductModel.value!!.product_price
            Log.e("data", "great work3 " + storeProductModel.value!!.product_quantity)

        } else {
            Log.e("data", "great work2 " + storeProductModel.value)

        }

    }

//    @BindingAdapter("app:src")
//    fun loadImage(view: ImageView, logoUrl: String?) {
//        if (logoUrl == null) {
//            view.setImageResource(android.R.drawable.ic_menu_share)
//        } else {
//            Picasso.get().load(logoUrl).into(view)
//        }
//    }

}