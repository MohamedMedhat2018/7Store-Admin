package com.example.firebaseauthwithmvvm.ui.add_product

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaseauthwithmvvm.R
import com.example.firebaseauthwithmvvm.data.model_test.Product
import com.squareup.picasso.Picasso

class dummyDataRecyclerView(var product: ArrayList<Product>, val context: Context) :
    RecyclerView.Adapter<dummyDataRecyclerView.ProductViewHolder>() {

    val TAG = this.javaClass.simpleName


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {

        val root = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_recycler_list_item, parent, false)
        return ProductViewHolder(root)
    }

    override fun getItemCount(): Int = product.size


    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {

        holder.tv_name.text = product.get(position).productName
        Log.e(TAG, "product  name " + product.get(position).productName)
        holder.tv_qnt.text = product.get(position).productQnt.toString()
        Log.e(TAG, "product  qnt " + product.get(position).productQnt)

        if (!product[position].productImg.isNullOrEmpty())
            Picasso.get().load(product.get(position).productImg).into(holder.iv_image)
        Log.e(TAG, "product  qnt " + product.get(position).productImg)

    }


    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var iv_image: ImageView
        lateinit var tv_name: TextView
        lateinit var tv_qnt: TextView

        init {

            iv_image = itemView.findViewById(R.id.product_image)
            tv_name = itemView.findViewById(R.id.product_name)
            tv_qnt = itemView.findViewById(R.id.product_qnt)
        }

    }


}

