package com.example.firebaseauthwithmvvm.ui.addItemToStore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.a7storenavigationdrawer.ui.addItemToStore.StoreItemViewModel
import com.example.firebaseauthwithmvvm.data.repository.UserRepository


//As we need the UserRepository inside the AuthViewModelFactoryModel we need a ViewModelFactory to generate
// the ViewModel with the required parameter.parameter.
//create viewModel
@Suppress("UNCHECKED_CAST")
class StoreItemViewModelFactory(private val userRepository: UserRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return StoreItemViewModel() as T
    }

}