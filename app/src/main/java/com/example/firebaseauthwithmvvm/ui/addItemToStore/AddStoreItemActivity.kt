package com.example.firebaseauthwithmvvm.ui.addItemToStore

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.firebaseauthwithmvvm.R

class AddStoreItemActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.hide()
        setContentView(R.layout.activity_add_storage_item)


    }
}
