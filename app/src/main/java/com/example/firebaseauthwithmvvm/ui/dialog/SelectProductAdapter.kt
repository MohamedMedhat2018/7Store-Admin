package com.example.firebaseauthwithmvvm.ui.dialog

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaseauthwithmvvm.R
import com.example.firebaseauthwithmvvm.models.StoreProduct
import com.example.firebaseauthwithmvvm.ui.addItemToStore.AddingToStoreListener
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class SelectProductAdapter(
    loadListener: AddingToStoreListener,
    var listOfProducts: List<StoreProduct>,
    var onProductSelected: OnProductSelected
) : RecyclerView.Adapter<SelectProductAdapter.ProductViewHolder>() {

    val TAG = "SelectProductAdapter"

    val itemStoreListener: AddingToStoreListener? = loadListener


    class ProductViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.select_product_item, parent, false)) {

        var title: TextView
        var icon: ImageView
        var itemHolder: View
        var progrssBar: ProgressBar


        init {
            title = itemView.findViewById(R.id.tvTitle)
            icon = itemView.findViewById(R.id.ivIcon)
            itemHolder = itemView.findViewById(R.id.ll_product_item_holder)
            progrssBar = itemHolder.findViewById(R.id.progressbar_loading_product)
        }

        fun bind(
            itemStoreListener: AddingToStoreListener?,
            product: StoreProduct,
            position: Int,
            onProductSelected: OnProductSelected
        ) {
            Log.e(TAG, "bind alert " + product.product_name)

            title.setText(product.product_name)
            Log.e(TAG, "title text " + title.text)
            Picasso.get().load(product.product_image)
//                .placeholder(R.drawable.user_profile_image_background)
//                .error(R.drawable.user_profile_image_background)
                .fit() //to prevent reload image when scroll
                .into(icon, object : Callback {
                    override fun onSuccess() {
                        Log.e(TAG, "success load image ")
                        progrssBar.visibility = View.GONE
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
            itemStoreListener?.onSuccess("Done")
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

        Log.e(ContentValues.TAG, "bind alert1 " + product.product_name)


        holder.bind(itemStoreListener, product, position, onProductSelected)
//        holder.bind(itemStoreListener, listOfProducts, position, onProductSelected)
    }
}