package com.example.a7storenavigationdrawer.ui.addItemToStore

import android.app.Application
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.firebaseauthwithmvvm.R
import com.example.firebaseauthwithmvvm.data.repository.StoreProductRepo
import com.example.firebaseauthwithmvvm.models.StoreProduct
import com.example.firebaseauthwithmvvm.ui.addItemToStore.ItemStoreListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.io.ByteArrayOutputStream
import java.io.IOException

//(private val repository: UserRepository)
class StoreItemViewModel(application: Application, private val repo: StoreProductRepo) :
    AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext

    private val TAG = StoreItemViewModel::class.java.simpleName

    //2 set upload image from User
    private var SetProductImage = MutableLiveData<String>()

    //3
    fun getProductImage(): LiveData<String> = SetProductImage

    private val disposables = CompositeDisposable()

    //uploaded image
//    var ProductImageUri: Uri? = null
    var ProductImageUri: MutableLiveData<Uri> = MutableLiveData()

    var productName: MutableLiveData<String> = MutableLiveData()

    var productQuantity = MutableLiveData<Int>().apply { value = 0 }

    var productCostValue = MutableLiveData<Int>().apply { value = 0 }

    var productPrice = MutableLiveData<Int>().apply { value = 0 }

    var uploadProductVisibility: MutableLiveData<String> = MutableLiveData()

    var itemStoreListener: ItemStoreListener? = null

    lateinit var productModel: StoreProduct

    val app = application

    private lateinit var mSelectedUri: Uri
    private lateinit var mUploadBytes: ByteArray

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

    fun increaseQuantity() {
        Log.e(TAG, "product test empty2 ${productQuantity.value!! + 1} ")
        productQuantity.value?.let { a ->
            //if (a == 0){
            productQuantity.value = a + 1
        }
    }

    fun decreaseQuantity() {
        Log.e(TAG, "product test empty2.1")
        productQuantity.value?.let { a ->
            if (productQuantity.value != 0)
                productQuantity.value = a - 1
        }
    }


    private fun uploadProductPhoto(imagePath: Uri?) {
        if (test(imagePath) != null) {
            Log.e(TAG, "TEST IMAEG ${test(imagePath)}")
//            executeUploadTask()
        }
    }

    lateinit var mbitmap: Bitmap
    fun test(vararg parms: Uri?): ByteArray? {
        Log.e(TAG, "111 ${parms}")
        try {
            //convert uri into bitmap
            mbitmap =
                MediaStore.Images.Media.getBitmap(app.contentResolver, parms[0])
            Log.e(TAG, "222 ${mbitmap}")

        } catch (e: IOException) {
            Log.e(TAG, "IOException ${e.message}")
        }


        //convert bitmap to byte array then compress it and upload it
        var byteImage: ByteArray? = null
        Log.e(TAG, "Before compress" + (byteImage?.count()?.div(1000000)))
        byteImage = getBytesFromBitmap(mbitmap, 100)
        Log.e(TAG, "After compress" + (byteImage?.count()?.div(1000000)))

        mUploadBytes = byteImage

        return byteImage
    }

    fun getBytesFromBitmap(bitmap: Bitmap, quality: Int): ByteArray {
        val stream: ByteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, stream)
        return stream.toByteArray()
    }

    private fun executeUploadTask() {
        Toast.makeText(app, "Upload Image", Toast.LENGTH_SHORT).show()

        val storeProductId: String? = FirebaseDatabase.getInstance().reference.push().key

        var storageReference: StorageReference = FirebaseStorage.getInstance().getReference()
            .child("StoreProducts/product_image/$storeProductId")

        val uploadTask: UploadTask = storageReference.putBytes(mUploadBytes)
        uploadTask.addOnSuccessListener(object : OnSuccessListener<UploadTask.TaskSnapshot> {
            override fun onSuccess(p0: UploadTask.TaskSnapshot?) {
                Log.e(TAG, "onSuccess: Post Success")
                val firebaseUri: Uri? = p0?.uploadSessionUri
                Log.e(TAG, "onSuccess: Firebase download Uri" + firebaseUri.toString())
                val reference: DatabaseReference = FirebaseDatabase.getInstance().reference
                //create a model
                if (storeProductId != null) {
                    reference
                        .child("StoreProduct")
                        .child(storeProductId)
//                        .setValue(YourModel)
                }
            }
        })
    }

    private fun checkFields(): Boolean {
        //for image
        if (ProductImageUri.value == null) {
            Log.e(TAG, "select an image")
            itemStoreListener?.onFailure(app.resources.getString(R.string.select_a_product_image))
            return false
        } else if (productName.value?.trim().isNullOrEmpty()) {
            Log.e(TAG, "product name is empty")
            itemStoreListener?.onFailure(app.resources.getString(R.string.add_product_name))
            return false
        } else if (productQuantity.value == 0) {
            Log.e(TAG, "product Quantity is 0")
            itemStoreListener?.onFailure(app.resources.getString(R.string.add_product_Quantity))
            return false
        } else if (productCostValue.value == 0) {
            Log.e(TAG, "product cost is empty")
            itemStoreListener?.onFailure(app.resources.getString(R.string.add_product_cost))
            return false
        } else if (productPrice.value == 0) {
            Log.e(TAG, "product price is empty")
            itemStoreListener?.onFailure(app.resources.getString(R.string.add_product_price))
            return false
        }
        return true
    }

    fun addProductToStore() {
        itemStoreListener?.onStarted()
        if (checkFields()) {

            uploadProductPhoto(ProductImageUri.value)

            productModel = StoreProduct(
                ProductImageUri.value.toString(),
                productName.value!!,
                productQuantity.value.toString(),
                productCostValue.value.toString(),
                productPrice.value.toString()
            )
            val disposable = repo.addNewProduct(productModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    itemStoreListener?.onSuccess()
                }, {
                    itemStoreListener?.onFailure(it.message!!)
                })
            disposables.add(disposable)
        }

        /*if (productName.value?.trim().equals("")) {
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
        Log.e(TAG, "it's name empty  ${productName.value?.trim()} and ${productQuantity.value}")*/

    }


/*    fun getImage(): LiveData<String> {

    }*/

    fun cancel() {
        Log.e(
            TAG,
            " name is ${productName.value} , ${productCostValue.value} , ${productPrice.value} , ${productQuantity.value}, ${ProductImageUri.value} "
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}

/*
*Unused
*create observer getter
*/

/*
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


*/
/*    var name = MutableLiveData<String>()
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
    }*//*


*/
/*
fun loadImage(){
    Picasso.get().load().into
}

    fun setImage() {
        imageView.postValue(image.toString())

        Picasso.get().load(uri).into(iv_store_product_image)

    }
     var name: String
        @Bindable
        @Nullable
        get() {
            return name
        }
        set(value) {
            if (name != value) {
                name = value
            }
        }


    @get:Bindable
    var name = StoreProduct()

    @Bindable
    fun getProductNmae(): String? {
        return name.product_name
    }

    @Bindable
    fun setProductNmae(name1: String) {
        if (name.product_name != name1) {
            name.product_name = name1

            notifyPropertyChanged(BR.product_name)
        }
    }
*//*



*/
/*    private val observableEvents = MutableLiveData<ViewModelEvent>()

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
    }*//*
}

*/
/*
@BindingAdapter("android:text")
fun setText(view: EditText, value: String) {
    if (value != null) {
        view.setText(Integer.parseInt(value))
    }

}

@InverseBindingAdapter(attribute = "android:text")
fun getText(view: EditText): Int {
    return Integer.parseInt(view.text.toString())
}
*//*

*/
