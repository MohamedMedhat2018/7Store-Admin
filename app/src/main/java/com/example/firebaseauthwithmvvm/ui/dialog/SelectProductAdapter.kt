package com.example.firebaseauthwithmvvm.ui.dialog

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaseauthwithmvvm.R
import com.example.firebaseauthwithmvvm.models.StoreProduct
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class SelectProductAdapter(
    var listOfProducts: List<StoreProduct>,
    var onProductSelected: OnProductSelected
) : RecyclerView.Adapter<SelectProductAdapter.ProductViewHolder>() {

    val TAG = "SelectProductAdapter"


    class ProductViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.select_product_item, parent, false)) {

        var title: TextView
        var icon: ImageView
        var itemHolder: View

        init {
            title = itemView.findViewById(R.id.tvTitle)
            icon = itemView.findViewById(R.id.ivIcon)
            itemHolder = itemView.findViewById(R.id.ll_product_item_holder)
        }

        fun bind(product: StoreProduct, position: Int, onProductSelected: OnProductSelected) {
            Log.e(TAG, "bind alert " + product.product_name)
            title.setText(product.product_name)
            Log.e(TAG, "title text " + title.text)
            Picasso.get().load(product.product_image).into(icon, object : Callback {
                override fun onSuccess() {
                    Log.e(TAG, "success load image ")
                }

                override fun onError(e: Exception?) {
                    Log.e(TAG, "error: load image  " + e?.message)
                }

            })


            itemHolder.setOnClickListener {

            }

            itemHolder.setOnClickListener {
                onProductSelected.onProductSelected(it, product, position)
                Log.e(TAG, "on click")

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflater =
            LayoutInflater.from(parent.context)
        Log.e(ContentValues.TAG, "onCreateViewHolder ")

        return ProductViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int {
        return listOfProducts.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        Log.e(ContentValues.TAG, "onBindViewHolder ")

        val product: StoreProduct = listOfProducts.get(position)

        holder.bind(product, position, onProductSelected)
    }
}