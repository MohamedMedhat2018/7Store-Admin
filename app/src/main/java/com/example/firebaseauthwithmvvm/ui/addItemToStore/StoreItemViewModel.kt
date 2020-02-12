package com.example.a7storenavigationdrawer.ui.addItemToStore

import android.net.Uri
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.firebaseauthwithmvvm.ui.addItemToStore.ViewModelEvent


//(private val repository: UserRepository)
class StoreItemViewModel : ViewModel() {


    //get upload image from User
    private var ProductImageMLiveData = MutableLiveData<String>()

    fun getProductImage(): LiveData<String> = ProductImageMLiveData

    var ProductImageUri: Uri? = null

    var product_name: String? = null
    var product_quantity: Int = 1
    var product_cost_value = 0.0
    var product_price = 0.0

    //onClick Upload Image
    fun startUploadProductImage() {
        //upload data
        ProductImageMLiveData.value = ""
        Log.e("StoreItemViewModel", "StartUploadImage")
    }


//    fun setProductCost():TextWatcher = {}


    var product_cost = object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
            Log.e("StoreItemViewModel", " afterTextChanged $p0")
//            product_cost_value = p0
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            Log.e("StoreItemViewModel", " beforeTextChanged $p0 and $p1 , $p2, $p3")
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            Log.e("StoreItemViewModel", " beforeTextChanged $p0 and $p1 , $p2, $p3")
        }

    }


    fun addProductToStore() {
        Log.e("StoreItemViewModel", "image uri1 $ProductImageMLiveData")
    }

    fun cancel() {
        Log.e("StoreItemViewModel", " name is ${name.value}")
    }


    /*
    *Unused
    *create observer getter
    */


//    var pro_name


    var name = MutableLiveData<String>()


//     var name: String
//        @Bindable
//        @Nullable
//        get() {
//            return name
//        }
//        set(value) {
//            if (name != value) {
//                name = value
//            }
//        }


//    @get:Bindable
//    var name = StoreProduct()
//
//    @Bindable
//    fun getProductNmae(): String? {
//        return name.product_name
//    }
//
//    @Bindable
//    fun setProductNmae(name1: String) {
//        if (name.product_name != name1) {
//            name.product_name = name1
//
//            notifyPropertyChanged(BR.product_name)
//        }
//    }


    private val observableEvents = MutableLiveData<ViewModelEvent>()

    // launch observer setter
    fun observeViewModelEvents(): LiveData<ViewModelEvent> = observableEvents

    //   Creates a MutableLiveData with value assigned to it. (event)
    fun PostViewModelEvent(event: ViewModelEvent) {
        Log.e("test", "teeest")
        observableEvents.postValue(event)
    }

    fun setImageView() {
        if (ProductImageUri != null) {
            Log.e("StoreItemViewModel", "image uri1 $ProductImageMLiveData")
        }
    }


}

