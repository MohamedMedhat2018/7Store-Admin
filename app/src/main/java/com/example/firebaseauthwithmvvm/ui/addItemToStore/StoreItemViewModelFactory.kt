package com.example.firebaseauthwithmvvm.ui.addItemToStore

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.a7storenavigationdrawer.ui.addItemToStore.StoreItemViewModel
import com.example.firebaseauthwithmvvm.data.repository.StoreProductRepo
import com.example.firebaseauthwithmvvm.data.repository.UserRepository


//As we need the UserRepository inside the AuthViewModelFactoryModel we need a ViewModelFactory to generate
// the ViewModel with the required parameter.parameter.
//create viewModel
@Suppress("UNCHECKED_CAST")
class StoreItemViewModelFactory(var application: Application, private val repo: StoreProductRepo) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return StoreItemViewModel(application, repo) as T
    }

}