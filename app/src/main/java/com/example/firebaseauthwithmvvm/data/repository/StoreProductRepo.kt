package com.example.firebaseauthwithmvvm.data.repository

import com.example.firebaseauthwithmvvm.data.firebase.FirebaseSource
import com.example.firebaseauthwithmvvm.models.Order
import com.example.firebaseauthwithmvvm.models.StoreProduct
import com.example.firebaseauthwithmvvm.ui.store.MyCallback

class StoreProductRepo(private val source: FirebaseSource) {

    fun addNewProduct(product: StoreProduct) = source.addStoreProduct(product)
//    fun uploadProductImage() = source.UploadImageToFirebase()

    fun getStoreProducts(myCallback: MyCallback) = source.getStoreProducts(myCallback)

    fun addNewOrder(order: Order) = source.addOrder(order)

}