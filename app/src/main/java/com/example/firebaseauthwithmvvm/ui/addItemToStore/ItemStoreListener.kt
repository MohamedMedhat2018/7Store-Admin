package com.example.firebaseauthwithmvvm.ui.addItemToStore

interface ItemStoreListener {

    fun onStarted()
    fun onSuccess()
    fun onFailure(message: String)
}