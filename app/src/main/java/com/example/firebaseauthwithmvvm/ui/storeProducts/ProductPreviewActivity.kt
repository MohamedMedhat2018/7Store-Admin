package com.example.firebaseauthwithmvvm.ui.storeProducts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firebaseauthwithmvvm.R

class test : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //hiding the toolbar
        supportActionBar!!.hide()

        setContentView(R.layout.store_item_row_test)
    }
}
