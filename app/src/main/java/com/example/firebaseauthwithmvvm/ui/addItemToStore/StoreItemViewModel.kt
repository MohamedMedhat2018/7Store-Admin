package com.example.a7storenavigationdrawer.ui.addItemToStore

import androidx.lifecycle.ViewModel
import com.example.firebaseauthwithmvvm.ui.addItemToStore.UploadImage
import java.lang.ref.WeakReference


class StoreItemViewModel : ViewModel() {

    lateinit var weakReference: WeakReference<UploadImage>


//    fun uploadProductImage() {
//        val intent: Intent = Intent()
//        intent.type = "image/*"
//        intent.setAction(Intent.ACTION_GET_CONTENT)
//        startActivityForResult(intent, PICK_IMAGE_REQUEST)
//    }
}