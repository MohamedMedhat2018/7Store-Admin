package com.example.firebaseauthwithmvvm.ui.store

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StoreViewModel : ViewModel() {

    private val textMLiveData = MutableLiveData<String>().apply {
        value = "Hi From Store View Model"
    }

    val textLiveData: LiveData<String> = textMLiveData

}