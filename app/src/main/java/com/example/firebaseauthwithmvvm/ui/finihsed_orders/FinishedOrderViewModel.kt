package com.example.firebaseauthwithmvvm.ui.finihsed_orders

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FinishedOrderViewModel : ViewModel() {

    private val textMLiveData = MutableLiveData<String>().apply {
        value = "Hi fROM Finished Order Live data"
    }

    val textLiveData: LiveData<String> = textMLiveData
}
