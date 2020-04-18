package com.example.firebaseauthwithmvvm.ui.store

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaseauthwithmvvm.R
import com.example.firebaseauthwithmvvm.databinding.ProductRecyclerListItemBinding
import com.example.firebaseauthwithmvvm.models.StoreProduct

class StoreAdapter(
    private val storeProduct: ArrayList<StoreProduct>,
    private var onItemClick: OnItemClick
) :
    RecyclerView.Adapter<StoreAdapter.storeProductViewHolder>() {

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
        holder.productRecyclerListItemBinding.root.setOnClickListener {
            onItemClick.onClick(it, storeProduct[position])
        }
    }
}