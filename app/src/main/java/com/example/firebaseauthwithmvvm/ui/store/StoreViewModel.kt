package com.example.firebaseauthwithmvvm.ui.store

import android.app.Application
import android.os.Build
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.databinding.BindingAdapter
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.firebaseauthwithmvvm.data.repository.StoreProductRepo
import com.example.firebaseauthwithmvvm.models.StoreProduct
import com.example.firebaseauthwithmvvm.utils.startAddProduct
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.android.material.appbar.AppBarLayout
import com.squareup.picasso.Picasso
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

    private val _storeProduct = MutableLiveData<ArrayList<StoreProduct>>()

    val storeProductModel: LiveData<ArrayList<StoreProduct>> = _storeProduct

    private val _storeProductTest = MutableLiveData<String>()

    val storeProductTest: LiveData<String> get() = _storeProductTest

    fun addProduct(view: View) {
        Log.e(TAG, "Test here")
        view.context.startAddProduct()
    }

//    lateinit var storeProductList: FirebaseRecyclerOptions<StoreProduct>
//    fun getProduct() {
//        //i need to make it observable
//        storeProductList = repo.getStoreProduct()
//    }

    fun getProducts() {
        repo.getStoreProducts(object : MyCallback {
            override fun onCallBack(listOfModel: ArrayList<StoreProduct>) {
                _storeProduct.value = listOfModel
            }
        })
        Log.e(TAG, "TEST DATA " + _storeProduct.value)
//        return repo.getStoreProducts()
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }


    var storeProductModelDetails = MutableLiveData<StoreProduct>()

    var getStoreProductModel: LiveData<StoreProduct> = storeProductModelDetails

    var ProductImage = MutableLiveData<String>()

//    fun getAProductImage(): LiveData<String> = ProductImage

    var productName: MutableLiveData<String> = MutableLiveData()

    var productQuantity = MutableLiveData<String>()

    var productCostValue = MutableLiveData<String>()

    var productPrice = MutableLiveData<String>()

    fun getData(storeProduct: StoreProduct) {
        var model: StoreProduct = StoreProduct()
        model = storeProduct
        storeProductModelDetails.value = model
        Log.e("data", "great work " + storeProductModelDetails.value!!.product_name)
        Log.e("data", "great workk " + storeProduct.product_name)
//        loadData()
    }

//    var test: MutableLiveData<String> = MutableLiveData()
//
//    fun test() {
//        test.value = "mm"
//        Log.e("data", "great work2.3")
//    }

//    fun test(){
//        storeProductModelDetails = ""
//    }

    fun loadData() {
        Log.e("data", "great work2.1 ")
        if (storeProductModelDetails.value != null) {
            productName.value = storeProductModelDetails.value!!.product_name

            ProductImage.value = storeProductModelDetails.value!!.product_image

            productQuantity.value = storeProductModelDetails.value!!.product_quantity
            productCostValue.value = storeProductModelDetails.value!!.product_material_Cost
            productPrice.value = storeProductModelDetails.value!!.product_price
            Log.e("data", "great work2 " + storeProductModelDetails.value!!.product_quantity)

        } else {
            Log.e("data", "great work2.2 " + storeProductModelDetails.value)

        }

    }


    @BindingAdapter("app:src")

    fun loadImage(view: ImageView, logoUrl: String?) {
        if (logoUrl == null) {
            view.setImageResource(android.R.drawable.ic_menu_share)
        } else {
            Picasso.get().load(logoUrl).into(view)
        }
    }


}