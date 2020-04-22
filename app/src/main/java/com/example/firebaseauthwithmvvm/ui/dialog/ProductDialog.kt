package com.example.firebaseauthwithmvvm.ui.dialog

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firebaseauthwithmvvm.R
import com.example.firebaseauthwithmvvm.models.StoreProduct
import com.example.firebaseauthwithmvvm.ui.addItemToStore.AddingToStoreListener
import com.example.firebaseauthwithmvvm.ui.addOrder.SelectOrCancelProductListener
import kotlinx.android.synthetic.main.layout_dialog_products.*
import kotlinx.android.synthetic.main.select_product_item.view.*

class ProductDialog : Dialog, AddingToStoreListener {

    private var ctxt: Context? = null
    private var listOfProducts: MutableList<StoreProduct>
    private var selectOrCancelProductListener: SelectOrCancelProductListener

    private val selectedProduct: MutableList<StoreProduct> = mutableListOf()

    //contain view of last added element
    private val selectedProductView: MutableList<View> = mutableListOf()

    var TAG = "ProductDialog"
    var preventShowDialogTwice = false


    constructor(
        context: Context,
        products: MutableList<StoreProduct>,
        selectOrCancelProductListener: SelectOrCancelProductListener
    ) : super(context) {
        this.ctxt = context
        this.listOfProducts = products
        this.selectOrCancelProductListener = selectOrCancelProductListener
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setCancelable(false)

        var rootViewProducts: View? = null

        rootViewProducts =
            LayoutInflater.from(ctxt).inflate(R.layout.layout_dialog_products, null, false)


        setContentView(rootViewProducts)

        onViewCreated(rootViewProducts)

    }

    private fun onViewCreated(rootViewProducts: View?) {

        setProductRecyclerViewAdapter()

        btn_Done.setOnClickListener {
            if (!selectedProduct.isNullOrEmpty())
                selectOrCancelProductListener.onProductSelected(selectedProduct[0])
            preventShowDialogTwice = false
            dismiss()
        }

        btn_cancel.setOnClickListener {
            preventShowDialogTwice = false
            dismiss()
        }

    }

    override fun setOnDismissListener(listener: DialogInterface.OnDismissListener?) {
        super.setOnDismissListener(listener)
        selectedProduct.clear()
    }

    private fun setProductRecyclerViewAdapter() {
        rv_List_of_select_a_product.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        Log.e(TAG, "setProductRecyclerViewAdapter " + listOfProducts.size)

        rv_List_of_select_a_product.adapter =
            SelectProductAdapter(this, listOfProducts, object : OnProductSelected {
                override fun onProductSelected(view: View, product: StoreProduct, pos: Int) {

                    if (selectedProduct.contains(product)) {
                        selectedProduct.remove(product)
                        selectedProductView.remove(view)
                        view.tvTitle.setBackgroundColor(
                            ContextCompat.getColor(context, R.color.white)
                        )
                    } else {
                        if (selectedProduct.isNotEmpty()) {
                            Log.e(TAG, "NotEmpty")
                            for (selectedView in selectedProductView) {
                                selectedView.tvTitle.setBackgroundColor(
                                    ContextCompat.getColor(context, R.color.white)
                                )
                            }
                            selectedProduct.clear()
                            selectedProduct.add(product)
                            selectedProductView.add(view)
                            view.tvTitle.setBackgroundColor(
                                ContextCompat.getColor(context, R.color.normal_grey)
                            )

                        } else {
                            Log.e(TAG, "Empty")
                            selectedProduct.add(product)
                            view.tvTitle.setBackgroundColor(
                                ContextCompat.getColor(context, R.color.normal_grey)
                            )
                            selectedProductView.add(view)
                        }
                    }
                }
            })
    }

    override fun onStarted() {
        Log.e(TAG, "Started from dialog")
    }

    override fun onSuccess(message: String?) {
        Log.e(TAG, "onSuccess from dialog" + message)
    }

    override fun onFailure(message: String?) {
        Log.e(TAG, "onFailure from dialog " + message)
    }


}