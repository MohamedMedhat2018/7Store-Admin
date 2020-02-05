package com.example.a7storenavigationdrawer.ui.addItemToStore

import android.content.Intent
import android.view.View
import androidx.annotation.Nullable
import androidx.lifecycle.ViewModel
import com.example.firebaseauthwithmvvm.data.repository.UserRepository
import com.example.firebaseauthwithmvvm.ui.addItemToStore.UploadImage
import java.lang.ref.WeakReference


class StoreItemViewModel(private val repository: UserRepository) : ViewModel() {

    val user by lazy {
        repository.currentUser()
    }

    @Nullable
    var weakReference: UploadImage? = null
//    var ll = weakReference

    fun onclick() {
        weakReference?.UploadImageDetails("n")
    }


//    fun uploadProductImage(view: View) {
//        val intent: Intent = Intent()
//        intent.type = "image/*"
//        intent.setAction(Intent.ACTION_GET_CONTENT)
//        view.startActivityForResult(intent, PICK_IMAGE_REQUEST)
//    }

}