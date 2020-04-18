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
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
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


    private val onEndLive: MutableLiveData<Boolean> = MutableLiveData()

    fun getOnEndLive(): MutableLiveData<Boolean> {
        return onEndLive
    }

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
            itemStoreListener?.onStarted()
            Log.e(TAG, "TEST Image ${test(imagePath)}")
            executeUploadTask()
        }
    }

    lateinit var mBitmap: Bitmap
    fun test(vararg params: Uri?): ByteArray? {
        Log.e(TAG, "111 $params")
        try {
            //convert uri into bitmap
            mBitmap = MediaStore.Images.Media.getBitmap(app.contentResolver, params[0])
            Log.e(TAG, "mBitmap $mBitmap")
        } catch (e: IOException) {
            itemStoreListener?.onFailure(e.message)
        }

        //convert bitmap to byte array then compress it and upload it
        var byteImage: ByteArray? = null
        Log.e(TAG, "Before compress" + (byteImage?.count()?.div(1000000)))
        byteImage = getBytesFromBitmap(mBitmap, 100)
        Log.e(TAG, "After compress" + (byteImage?.count()?.div(1000000)) + byteImage)

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
        Log.e(TAG, "storeProductId $storeProductId")

        val referance: DatabaseReference = FirebaseDatabase.getInstance().reference

        val storageReference: StorageReference = FirebaseStorage.getInstance().reference
            .child("StoreProducts/product_image/$storeProductId")

        val uploadTask: UploadTask = storageReference.putBytes(mUploadBytes)

//        val disposableUploadImage = repo

        uploadTask.addOnSuccessListener { taskSnapshot ->
            if (taskSnapshot.metadata != null) {
                val result = taskSnapshot.storage.downloadUrl
                result.addOnSuccessListener { uri: Uri? ->
                    val imageUrl = uri.toString()
                    Log.e(TAG, "uri $imageUrl")

                    productModel = StoreProduct(
                        "",
                        imageUrl,
                        productName.value!!,
                        productQuantity.value.toString(),
                        productCostValue.value.toString(),
                        productPrice.value.toString()
                    )

                    val disposable = repo.addNewProduct(productModel)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            itemStoreListener?.onSuccess(context.getString(R.string.add_product_success))
                        }, {
                            itemStoreListener?.onFailure(it.message!!)
                        })
                    disposables.add(disposable)
                }.addOnFailureListener {
                    itemStoreListener?.onFailure(it.message)
                }

//                    if (storeProductId != null) {
//                        referance.child("StoreProduct")
//                            .child(storeProductId)
//                            .setValue(productModel)
//                            .addOnSuccessListener {
//                                itemStoreListener?.onSuccess(context.getString(R.string.add_product_success))
//                                //move to preview store product
//                            }.addOnFailureListener {
//                                itemStoreListener?.onFailure(it.message)
//                            }
//                        Log.e(TAG, "onSuccess: Firebase 2 download Uri $storeProductId")
//                    }


//                }.addOnFailureListener { exception ->
//                    itemStoreListener?.onFailure(exception.message)
//                }
            }
        }.addOnFailureListener { exception ->
            itemStoreListener?.onFailure(exception.message)
        }

//        uploadTask.addOnCompleteListener { task ->
//            if (task.isSuccessful) {
//                val downloadUri = task.result
//
//                val url = downloadUri?.metadata?.reference?.downloadUrl
//                val url2 = task?.result?.uploadSessionUri
//                val url3 =
//                Log.e(
//                    TAG,
//                    "onSuccess: Firebase 2 download Uri ${url.toString()}" + "and $downloadUri"
//                )
//
//
//            }
//        }

//        uploadTask.addOnSuccessListener { p0 ->
//            Log.e(TAG, "Failder: Post Success")
//            val firebaseUri: Uri? = p0?.uploadSessionUri
//            Log.e(TAG, "onSuccess: Firebase download Uri $firebaseUri")
//            val reference: DatabaseReference = FirebaseDatabase.getInstance().reference
//            //create a model
//            Log.e(TAG, "onSuccess: reference $reference")
//
//            productModel = StoreProduct(
//                ProductImageUri.value.toString(),
//                productName.value!!,
//                productQuantity.value.toString(),
//                productCostValue.value.toString(),
//                productPrice.value.toString()
//            )
//
//            if (storeProductId != null) {
//                //                    reference.child("StoreProduct").child(storeProductId)
//                //                        .setValue(YourModel)
//                Log.e(TAG, "onSuccess: Firebase 2 download Uri $storeProductId")
//
//            }
//        }.addOnFailureListener(OnFailureListener { exception ->
//            Log.e(TAG, "exception: Post error ${exception.message}")
//        })
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

//            productModel = StoreProduct(
//                ProductImageUri.value.toString(),
//                productName.value!!,
//                productQuantity.value.toString(),
//                productCostValue.value.toString(),
//                productPrice.value.toString()
//            )
//            val disposable = repo.addNewProduct(productModel)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({
//                    itemStoreListener?.onSuccess()
//                }, {
//                    itemStoreListener?.onFailure(it.message!!)
//                })
//            disposables.add(disposable)
//        }

//            if (productName.value?.trim().equals("")) {
//                Log.e(TAG, "it's empty name")
//            }
//
//            if (productName.value?.trim().equals(null)) {
//                Log.e(TAG, "it's name null ")
//            }
//
//            if (productName.value?.trim() == null) {
//                Log.e(TAG, "it's name null 2 ")
//            }
//
//            if (productName.value?.trim().isNullOrEmpty()) {
//                Log.e(TAG, "it's name empty ")
//            }
//        Log.e(TAG, "image uri1 ${SetProductImage.value}  and ${getProductImage().value}  " )
//        Log.e(TAG, "image uri2 ${ProductImageUri.value} ")
//        Log.e(TAG, "it's name empty  ${productName.value?.trim()} and ${productQuantity.value}")

        }


/*    fun getImage(): LiveData<String> {

    }*/
    }

    fun cancel() {
//        Log.e(TAG, " name is ${productName.value} , ${productCostValue.value} , ${productPrice.value} , ${productQuantity.value}, ${ProductImageUri.value} ")
        onEndLive.value = true
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
