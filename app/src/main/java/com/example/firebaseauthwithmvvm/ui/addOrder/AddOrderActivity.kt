package com.example.firebaseauthwithmvvm.ui.addOrder

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.firebaseauthwithmvvm.R
import com.example.firebaseauthwithmvvm.data.firebase.FirebaseSource
import com.example.firebaseauthwithmvvm.data.repository.StoreProductRepo
import com.example.firebaseauthwithmvvm.databinding.ActivityAddOrderBinding
import com.example.firebaseauthwithmvvm.ui.addItemToStore.AddingToStoreListener
import com.example.firebaseauthwithmvvm.utils.startAddNewOrder
import com.example.firebaseauthwithmvvm.utils.stratOrdersFragment
import dmax.dialog.SpotsDialog
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein

class AddOrderActivity : AppCompatActivity(), KodeinAware, AddingToStoreListener {

    private lateinit var viewModel: AddOrderViewModel

    lateinit var sDialog: SpotsDialog



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_order)

//        supportActionBar?.show()
        actionBar?.hide()

        sDialog = SpotsDialog.Builder().setContext(this)
            .setCancelable(false)
            .build() as SpotsDialog

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
        viewModel.addOrderListener = this

    }

    override val kodein by kodein()

    override fun onStarted() {
        Log.e(this.toString(), "on Start")
        sDialog.show()
    }

    override fun onSuccess(message: String?) {
        Log.e(this.toString(), "on Success")
        sDialog.hide()
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        this.stratOrdersFragment()
    }

    override fun onFailure(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        Log.e("AddOrderActivity", "on Error $message")
    }

}