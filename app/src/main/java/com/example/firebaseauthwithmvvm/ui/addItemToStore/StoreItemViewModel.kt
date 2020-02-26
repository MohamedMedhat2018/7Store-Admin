package com.example.a7storenavigationdrawer.ui.addItemToStore

import android.net.Uri
import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.firebaseauthwithmvvm.data.repository.StoreProductRepo
import com.example.firebaseauthwithmvvm.ui.addItemToStore.ViewModelEvent
import com.squareup.picasso.Picasso
import kotlin.math.log


//(private val repository: UserRepository)
class StoreItemViewModel(private val repo: StoreProductRepo) : ViewModel() {

    private val TAG = StoreItemViewModel::class.java.simpleName

    //2 set upload image from User
    private var SetProductImage = MutableLiveData<String>()

    //3
    fun getProductImage(): LiveData<String> = SetProductImage

    //uploaded image
//    var ProductImageUri: Uri? = null
    var ProductImageUri: MutableLiveData<Uri> = MutableLiveData()

    var productName: MutableLiveData<String> = MutableLiveData()
    var productQuantity: MutableLiveData<Int> = MutableLiveData()
    var productCostValue: MutableLiveData<String> = MutableLiveData()
    var productPrice: MutableLiveData<String> = MutableLiveData()

    var uploadProductVisibility: MutableLiveData<String> = MutableLiveData()

    /*fun setVisibility() {
        if (ProductImageUri != null) {

        }
    }*/

    //1 onClick Upload Image
    fun onClickUploadProductImage() {
        //upload data
        SetProductImage.value = ""
        Log.e(TAG, "StartUploadImage")
    }

//    fun setProductCost():TextWatcher = {}


    fun increaseQ() {
        if (productQuantity.value == null) {
            productQuantity.value = 1
            Log.e(TAG, "product test empty1")
        } else {
            Log.e(TAG, "product test empty2")
            productQuantity.value = (productQuantity.value!! + 1)
        }
    }

    fun decreaseQ() {

    }


    fun checkFields() {
//        || getProductImage().equals("")
        //for image
        if (ProductImageUri.value == null) {
            Log.e(TAG, "select an image")
        } else if (productName.value?.trim().isNullOrEmpty()) {
            Log.e(TAG, "product name is empty")
        } else if (productQuantity.value == null) {
            Log.e(TAG, "product Quantity is empty")
        } else if (productCostValue.value?.trim().isNullOrEmpty()) {
            Log.e(TAG, "product cost is empty")
        } else if (productPrice.value?.trim().isNullOrEmpty()) {
            Log.e(TAG, "product price is empty")
        }
    }


    fun addProductToStore() {

        checkFields()

        if (productName.value?.trim().equals("")) {
            Log.e(TAG, "it's empty name")
        }

        if (productName.value?.trim().equals(null)) {
            Log.e(TAG, "it's name null ")
        }

        if (productName.value?.trim() == null) {
            Log.e(TAG, "it's name null 2 ")
        }

        if (productName.value?.trim().isNullOrEmpty()) {
            Log.e(TAG, "it's name empty ")
        }

//        Log.e(TAG, "image uri1 ${SetProductImage.value}  and ${getProductImage().value}  " )
        Log.e(TAG, "image uri2 ${ProductImageUri.value} ")
        Log.e(TAG, "it's name empty  ${productName.value?.trim()} and ${productQuantity.value}")


//        repo.addNewProduct()
    }

    fun cancel() {
        Log.e(
            TAG,
            " name is ${productName.value} , ${productCostValue.value} , ${productPrice.value} , ${productQuantity.value}, ${ProductImageUri.value} "
        )
    }


    /*
    *Unused
    *create observer getter
    */

//    var product_cost = object : TextWatcher {
//        override fun afterTextChanged(p0: Editable?) {
//            Log.e(TAG, " afterTextChanged $p0")
////            product_cost_value = p0
//        }
//
//        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//            Log.e(TAG, " beforeTextChanged $p0 and $p1 , $p2, $p3")
//        }
//
//        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//            Log.e(TAG, " beforeTextChanged $p0 and $p1 , $p2, $p3")
//        }
//    }

//    var pro_name


    var name = MutableLiveData<String>()
    var image = MutableLiveData<Uri>()
    var imageView = MutableLiveData<String>()


    @BindingAdapter("app:imageUrl")
    fun LoadImage(imageView: ImageView, uri: Uri) {
        if (uri != null) {
            Picasso.get().load(uri).into(imageView)
            Log.e(TAG, " image is, ${image.value}, $uri, $imageView ")
        } else {
            Log.e(TAG, " image is null")
        }
    }

//fun loadImage(){
//    Picasso.get().load().into
//}

//    fun setImage() {
//        imageView.postValue(image.toString())
//
//        Picasso.get().load(uri).into(iv_store_product_image)
//
//    }
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
        Log.e(TAG, "teeest")
        observableEvents.postValue(event)
    }

    fun setImageView() {
        if (ProductImageUri != null) {
            Log.e(TAG, "image uri1 $SetProductImage")
        }
    }


}

