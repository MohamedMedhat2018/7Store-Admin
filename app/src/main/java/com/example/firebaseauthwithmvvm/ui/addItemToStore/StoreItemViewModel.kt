package com.example.a7storenavigationdrawer.ui.addItemToStore

import android.net.Uri
import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.firebaseauthwithmvvm.ui.addItemToStore.ViewModelEvent
import com.squareup.picasso.Picasso

@BindingAdapter("android:src")
fun setImageUri(view: ImageView, imageUri: Uri?) {
    view.setImageURI(imageUri)
    Picasso.get().load(imageUri).into(view)
    Log.e("setImageUri", "image uri1 Test $imageUri")
}

//(private val repository: UserRepository)
class StoreItemViewModel : ViewModel() {

    //    get upload image from User
    private var ProductImageMLiveData = MutableLiveData<String>()

    fun getProductImage(): LiveData<String> = ProductImageMLiveData

    var uri: Uri
        get() {
            return uri
        }
        set(value) {
            uri = value
        }

//    fun checkUri(): Boolean{
//        if (uri !=null){
//            return true
//        }
//        Log.e("StoreItemViewModel", "image uri1 $uri")
//
//        return false
//    }


    fun setImageView() {
        if (uri != null) {
            Log.e("StoreItemViewModel", "image uri1 $uri")
        }
    }



    //onClick
    fun startUploadImage() {
        //upload data
        ProductImageMLiveData.value = ""
        Log.e("StoreItemViewModel", "StartUploadImage")
    }

    //create observer getter
    private val observableEvents = MutableLiveData<ViewModelEvent>()

    // launch observer setter
    fun observeViewModelEvents(): LiveData<ViewModelEvent> = observableEvents

    //   Creates a MutableLiveData with value assigned to it. (event)
    fun PostViewModelEvent(event: ViewModelEvent) {
        Log.e("test", "teeest")
        observableEvents.postValue(event)
    }
}

