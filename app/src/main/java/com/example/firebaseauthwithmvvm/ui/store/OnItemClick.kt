package com.example.firebaseauthwithmvvm.ui.store

import android.view.View
import com.example.firebaseauthwithmvvm.models.StoreProduct

interface OnItemClick {
    fun onClick(view: View, storeProduct: StoreProduct)
}