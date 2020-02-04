package com.example.firebaseauthwithmvvm.ui.addItemToStore

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.firebaseauthwithmvvm.R

class AddStoreItemActivity : AppCompatActivity(), UploadImage {

    private val PICKER_IMAGE_REQUEST = 1
    lateinit var imageUri: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //hiding the toolbar
        supportActionBar!!.hide()

        setContentView(R.layout.activity_add_storage_item)

    }

    override fun UploadImageDetails(imageId: String) {
        val intent: Intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, PICKER_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICKER_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            imageUri = data.data!!

//            Picasso.get().load(imageUri).into()

        }

    }


}
