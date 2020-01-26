package com.example.firebaseauthwithmvvm.data.repository

import com.example.firebaseauthwithmvvm.data.firebase.FirebaseSource

class UserRepository(private val firebase: FirebaseSource) {
    //receive from FirebaseSource
    fun login(email: String, pass: String) = firebase.login(email, pass)
    fun register(email: String, pass: String) = firebase.register(email, pass)
    fun currentUser() = firebase.currentUser()
    fun logout() = firebase.logout()



}