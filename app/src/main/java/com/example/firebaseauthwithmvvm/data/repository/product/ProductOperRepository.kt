package com.example.firebaseauthwithmvvm.data.repository.product

import com.example.firebaseauthwithmvvm.data.firebase.product.ProductFireSource
import com.example.firebaseauthwithmvvm.models.Product

class ProductOperRepository(private val source: ProductFireSource){

    fun addNewOrder(product: Product) = source.addNewOrder(product)


}