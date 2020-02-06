package com.example.firebaseauthwithmvvm.ui.addItemToStore

import android.util.Log

abstract class ViewModelEvent {


    var handler: Boolean = false
        private set

    //do all activity work here :) :) :)
    fun handle(addStoreItemActivity: AddStoreItemActivity) {
        handler = true
        val TAG = addStoreItemActivity::class.java.simpleName
        Log.e(TAG, "El7amdollah happpppy")
    }

}