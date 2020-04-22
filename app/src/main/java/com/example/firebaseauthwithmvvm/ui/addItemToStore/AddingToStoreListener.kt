package com.example.firebaseauthwithmvvm.ui.addItemToStore

interface AddingToStoreListener {

    fun onStarted()
    fun onSuccess(message: String?)
    fun onFailure(message: String?)
}