package com.example.firebaseauthwithmvvm.data.repository

import com.example.firebaseauthwithmvvm.data.firebase.FirebaseSource
import com.example.firebaseauthwithmvvm.models.StoreProduct

class StoreProductRepo(private val source: FirebaseSource) {

    fun addNewProduct(product: StoreProduct) = source.addStoreProduct(product)
}