package com.example.a7storenavigationdrawer.ui.addItemToStore

import android.app.Activity
import android.content.Intent
import android.view.View
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel

class StoreItemViewModel : ViewModel() {


    fun uploadProductImage() {
        val intent: Intent = Intent()
        intent.type = "image/*"
        intent.setAction(Intent.ACTION_GET_CONTENT)
//        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }
}