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

class StoreAdapterFirebaseUi(options: FirebaseRecyclerOptions<StoreProduct>) :
    FirebaseRecyclerAdapter<StoreProduct, StoreAdapterFirebaseUi.viewHolder>(options) {


    inner class viewHolder(val productRecyclerListItemBinding: ProductRecyclerListItemBinding) :
        RecyclerView.ViewHolder(productRecyclerListItemBinding.root) {

    }

//    override fun getItemCount(): Int {
//        return super.getItemCount()
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = viewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.product_recycler_list_item, parent, false
        )
    )

    override fun onBindViewHolder(holder: viewHolder, position: Int, model: StoreProduct) {
        holder.productRecyclerListItemBinding.viewmodel = model
        Log.e("TTT", "MODEL IS $model")
    }
}