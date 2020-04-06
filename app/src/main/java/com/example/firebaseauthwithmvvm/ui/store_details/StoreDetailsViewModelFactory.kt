package com.example.firebaseauthwithmvvm.ui.store_details

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.a7storenavigationdrawer.ui.addItemToStore.StoreItemViewModel
import com.example.firebaseauthwithmvvm.data.repository.StoreProductRepo

@Suppress("UNCHECKED_CAST")
class StoreDetailsViewModelFactory() :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return StoreDetailsViewModel() as T
    }

}