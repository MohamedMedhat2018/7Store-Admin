package com.example.firebaseauthwithmvvm.ui.store

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaseauthwithmvvm.R
import com.example.firebaseauthwithmvvm.databinding.ProductRecyclerListItemBinding
import com.example.firebaseauthwithmvvm.models.StoreProduct
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions

class StoreAdapter2(private val storeProduct: ArrayList<StoreProduct>) :
    RecyclerView.Adapter<StoreAdapter2.storeProductViewHolder>() {

    class storeProductViewHolder(val productRecyclerListItemBinding: ProductRecyclerListItemBinding) :
        RecyclerView.ViewHolder(productRecyclerListItemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        storeProductViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.product_recycler_list_item,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = storeProduct.size

    override fun onBindViewHolder(holder: storeProductViewHolder, position: Int) {
        holder.productRecyclerListItemBinding.viewmodel = storeProduct[position]
    }
}