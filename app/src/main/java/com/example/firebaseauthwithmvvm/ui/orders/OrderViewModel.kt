package com.example.firebaseauthwithmvvm.ui.orders

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.firebaseauthwithmvvm.data.repository.StoreProductRepo
import com.example.firebaseauthwithmvvm.models.StoreProduct
import com.example.firebaseauthwithmvvm.ui.store.MyCallback
import com.example.firebaseauthwithmvvm.utils.startAddNewOrder

class OrderViewModel(application: Application, private val repo: StoreProductRepo) :
    AndroidViewModel(application) {

    //test if u want to set value to MutableLiveData while declaration
//    private val textMLiveData = MutableLiveData<String>().apply {
//        value = "Hi from Order View Model"
//    }
//
//    val textLiveDta: LiveData<String> = textMLiveData

    var TAG = this.javaClass.simpleName


    fun addNewOrder(view: View) {
        Log.e(TAG, "good")
        view.context.startAddNewOrder()
    }


}
