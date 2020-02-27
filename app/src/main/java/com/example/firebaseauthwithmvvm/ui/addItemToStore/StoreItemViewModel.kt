package com.example.a7storenavigationdrawer.ui.addItemToStore

import android.app.Application
import android.net.Uri
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.firebaseauthwithmvvm.R
import com.example.firebaseauthwithmvvm.data.repository.StoreProductRepo
import com.example.firebaseauthwithmvvm.ui.addItemToStore.ItemStoreListener
import com.example.firebaseauthwithmvvm.ui.addItemToStore.ViewModelEvent
import com.squareup.picasso.Picasso


//(private val repository: UserRepository)
class StoreItemViewModel(application: Application, private val repo: StoreProductRepo) :
    AndroidViewModel(application) {

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

    var itemStoreListener: ItemStoreListener? = null

    val app = application

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


    fun increaseQuantity() {
        if (productQuantity.value == null || productQuantity.value!! == 0) {
            productQuantity.value = 1
            Log.e(TAG, "product test empty1")
        } else {
            Log.e(TAG, "product test empty2 ${productQuantity.value} ")
            productQuantity.value!!.plus(1)
        }
    }

    fun decreaseQuantity() {
        if (productQuantity.value == null || productQuantity.value == 0) {
            productQuantity.value = 0
            Log.e(TAG, "product test empty1")
        } else {
            Log.e(TAG, "product test empty2")
            productQuantity.value!!.minus(1)
        }
    }


    fun checkFields() {
        //for image
        if (ProductImageUri.value == null) {
            Log.e(TAG, "select an image")
            itemStoreListener?.onFailure(app.resources.getString(R.string.select_a_product_image))
        } else if (productName.value?.trim().isNullOrEmpty()) {
            Log.e(TAG, "product name is empty")
            itemStoreListener?.onFailure(app.resources.getString(R.string.add_product_name))
        } else if (productQuantity.value == null) {
            Log.e(TAG, "product Quantity is empty")
            itemStoreListener?.onFailure(app.resources.getString(R.string.add_product_Quantity))
        } else if (productCostValue.value?.trim().isNullOrEmpty()) {
            Log.e(TAG, "product cost is empty")
            itemStoreListener?.onFailure(app.resources.getString(R.string.add_product_cost))
        } else if (productPrice.value?.trim().isNullOrEmpty()) {
            Log.e(TAG, "product price is empty")
            itemStoreListener?.onFailure(app.resources.getString(R.string.add_product_price))
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

@BindingAdapter("android:text")
fun setText(view: EditText, value: String) {
    view.setText(Integer.parseInt(value))
}

@InverseBindingAdapter(attribute = "android:text")
fun getText(view: EditText): Int {
    return Integer.parseInt(view.text.toString())
}


