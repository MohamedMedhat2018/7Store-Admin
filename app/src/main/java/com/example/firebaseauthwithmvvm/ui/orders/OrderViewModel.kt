package com.example.firebaseauthwithmvvm.ui.orders

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OrderViewModel : ViewModel() {

    private val textMLiveData = MutableLiveData<String>().apply {
        value = "Hi from Order View Model"
    }

    val textLiveDta: LiveData<String> = textMLiveData

}
