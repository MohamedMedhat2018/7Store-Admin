package com.example.firebaseauthwithmvvm.ui.addOrder

import com.example.firebaseauthwithmvvm.models.StoreProduct

interface SelectOrCancelProductListener {

    fun onProductSelected(selectedProduct: StoreProduct)
    fun onProductCanceled()
}