package com.example.firebaseauthwithmvvm.ui.store

import android.os.Build
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.lifecycle.ViewModel
import com.example.firebaseauthwithmvvm.utils.startAddProduct
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.product_content.*
import kotlin.math.abs

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

    fun getProduct(view: View) {

    }
}