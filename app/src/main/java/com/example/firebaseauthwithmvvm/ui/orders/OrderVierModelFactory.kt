package com.example.firebaseauthwithmvvm.ui.orders

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.firebaseauthwithmvvm.data.repository.StoreProductRepo

@Suppress("UNCHECKED_CAST")
class OrderVierModelFactory(var application: Application, private val repo: StoreProductRepo) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return OrderViewModel(application, repo) as T
    }
}