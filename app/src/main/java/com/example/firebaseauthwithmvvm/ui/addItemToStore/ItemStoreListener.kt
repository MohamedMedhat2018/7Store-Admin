package com.example.firebaseauthwithmvvm.ui.addItemToStore

interface ItemStoreListener {

    fun onStarted()
    fun onSuccess(message: String?)
    fun onFailure(message: String?)
}