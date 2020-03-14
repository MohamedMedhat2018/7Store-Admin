package com.example.firebaseauthwithmvvm.ui.store

import com.example.firebaseauthwithmvvm.models.StoreProduct

interface MyCallback {
    fun onCallBack(listOfModel: ArrayList<StoreProduct>)
}