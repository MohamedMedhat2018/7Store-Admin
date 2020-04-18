package com.example.firebaseauthwithmvvm.ui.dialog

import android.view.View
import com.example.firebaseauthwithmvvm.models.StoreProduct

public interface OnProductSelected {
    fun onProductSelected(view: View, product: StoreProduct, pos: Int)
}