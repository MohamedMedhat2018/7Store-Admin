package com.example.firebaseauthwithmvvm.ui.home2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.a7storenavigationdrawer.ui.addItemToStore.StoreItemViewModel
import com.example.firebaseauthwithmvvm.data.repository.UserRepository

class HomeViewModelFactory(private val repository: UserRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(repository) as T
    }
}