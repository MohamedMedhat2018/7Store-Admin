package com.example.firebaseauthwithmvvm.ui.store

import android.app.Application
import android.os.Build
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.firebaseauthwithmvvm.data.repository.StoreProductRepo
import com.example.firebaseauthwithmvvm.models.StoreProduct
import com.example.firebaseauthwithmvvm.utils.startAddProduct
import com.google.android.material.appbar.AppBarLayout
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.product_content.*
import kotlin.math.abs

class StoreViewModel(application: Application, private val repo: StoreProductRepo) :
    AndroidViewModel(application) {

    var TAG = this.javaClass.simpleName
    private val disposables = CompositeDisposable()

//    private val textMLiveData = MutableLiveData<String>().apply {
//        value = "Hi From Store View Model"
//    }
//
//    val textLiveData: LiveData<String> = textMLiveData

    private val _storeProduct = MutableLiveData<List<StoreProduct>>()
    private val _storeProductTest = MutableLiveData<String>()

    val storeproductTest: LiveData<String> get() = _storeProductTest

    fun addProduct(view: View) {
        Log.e(TAG, "Test here")
        view.context.startAddProduct()
    }

    fun getProduct() {
        val disposable = repo.getStoreProduct()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _storeProductTest.value = "cool"
                Log.e(TAG, "GREAT JOB ")

            }, {
                Log.e(TAG, "Error StoreView Model $it")
            })
        disposables.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}