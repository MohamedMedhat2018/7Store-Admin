package com.example.firebaseauthwithmvvm.ui.store

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import com.example.firebaseauthwithmvvm.utils.startAddProduct

class StoreViewModel : ViewModel() {

    var TAG = this.javaClass.simpleName

//    private val textMLiveData = MutableLiveData<String>().apply {
//        value = "Hi From Store View Model"
//    }
//
//    val textLiveData: LiveData<String> = textMLiveData


    fun addProduct(view: View) {
        Log.e(TAG, "Test here")
        view.context.startAddProduct()

    }

}