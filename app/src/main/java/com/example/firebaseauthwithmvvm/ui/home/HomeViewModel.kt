package com.example.firebaseauthwithmvvm.ui.home

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.firebaseauthwithmvvm.data.repository.UserRepository
import com.example.firebaseauthwithmvvm.utils.startLoginActivity

class HomeViewModel(private val repository: UserRepository) : ViewModel() {

    val user by lazy {
        repository.currentUser()
    }

    fun logout(view: View) {
        repository.logout()
        view.context.startLoginActivity()
    }

}