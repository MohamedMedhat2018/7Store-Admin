package com.example.firebaseauthwithmvvm.ui.store_details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.firebaseauthwithmvvm.R
import com.example.firebaseauthwithmvvm.databinding.ActivityStoreDetailsBinding
import com.example.firebaseauthwithmvvm.models.StoreProduct
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein

class StoreDetailsActivity : AppCompatActivity(), KodeinAware {

    lateinit var viewModel: StoreDetailsViewModel

    lateinit var ProductDetails: StoreProduct
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_details)

        supportActionBar?.hide()

        val binding: ActivityStoreDetailsBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_store_details)

        viewModel = ViewModelProviders.of(
            this
        )
            .get(StoreDetailsViewModel::class.java)
        Log.e("data", "great workxx " + viewModel.loadData())

//        viewModel.loadData()

        viewModel.getStoreProductModel.observe(this@StoreDetailsActivity, Observer {
            viewModel.loadData()
            Log.e("data", "great work4")
        })

//        viewModel.test.observe(this@StoreDetailsActivity, Observer {
//            Log.e("data", "great work4.1  $it")
//        })

//        if (Prefs.contains(Constants.PRODUCT)) {
//            val gson: Gson = Gson()
//            val json = Prefs.getString(Constants.PRODUCT, "")
//            ProductDetails = gson.fromJson(json, StoreProduct::class.java)
//
//            Log.e("Data", "Get details" + ProductDetails.product_name)
//
//            viewModel.getData(ProductDetails)
//        }

        viewModel.data()

        binding.viewModel = viewModel

        binding.lifecycleOwner = this

    }

    override val kodein by kodein()

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

//        if (intent != null) {
//            if (intent.hasExtra(Constants.STORE_PRODUCTS)) {
//                val bundle = intent.extras
//
//                var storeProduct: StoreProduct =
//                    bundle?.getSerializable(Constants.STORE_PRODUCTS) as StoreProduct
//
//                Log.e("Text2", "Get data" + storeProduct.product_name)
//
//
//            }
//        }

    }


}


