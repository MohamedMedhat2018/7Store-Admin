package com.example.firebaseauthwithmvvm.ui.test

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaseauthwithmvvm.R

class dummyDataRecyclerView : RecyclerView.Adapter<dummyDataRecyclerView.ProductViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {

        val root = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_recycler_list_item, parent, false)
        return ProductViewHolder(root)
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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

