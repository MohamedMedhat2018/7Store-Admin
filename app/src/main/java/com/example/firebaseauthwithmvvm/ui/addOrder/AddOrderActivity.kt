package com.example.firebaseauthwithmvvm.ui.addOrder

import android.app.Activity
import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.firebaseauthwithmvvm.R
import com.example.firebaseauthwithmvvm.data.firebase.FirebaseSource
import com.example.firebaseauthwithmvvm.data.repository.StoreProductRepo
import com.example.firebaseauthwithmvvm.databinding.ActivityAddOrderBinding
import com.example.firebaseauthwithmvvm.ui.orders.OrderViewModel
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein

class AddOrderActivity : AppCompatActivity(), KodeinAware {

    private lateinit var viewModel: AddOrderViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_order)

//        supportActionBar?.show()
        actionBar?.hide()


//        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

//        viewModel = ViewModelProviders.of(this).get(OrderViewModel::class.java)

        val binding: ActivityAddOrderBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_add_order)

        viewModel = ViewModelProviders.of(
            this, AddOrderViewModelFactory(
                application, StoreProductRepo(FirebaseSource())
            )
        ).get(AddOrderViewModel::class.java)

//        viewModel.getproducts()

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

    }

    override val kodein by kodein()

}