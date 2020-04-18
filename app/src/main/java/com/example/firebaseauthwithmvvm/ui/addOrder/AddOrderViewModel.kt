package com.example.firebaseauthwithmvvm.ui.addOrder

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.firebaseauthwithmvvm.data.repository.StoreProductRepo
import com.example.firebaseauthwithmvvm.models.StoreProduct
import com.example.firebaseauthwithmvvm.ui.dialog.ProductDialog
import com.example.firebaseauthwithmvvm.ui.store.MyCallback

class AddOrderViewModel(application: Application, private val repo: StoreProductRepo) :
    AndroidViewModel(application) {

    val TAG = AddOrderViewModel::class.java.simpleName

    val context = getApplication<Application>().applicationContext

    private val selectItem: MutableLiveData<Boolean> = MutableLiveData()

    private val storeProductList: MutableLiveData<ArrayList<StoreProduct>> = MutableLiveData()

    private val selectedProductName: MutableLiveData<String> = MutableLiveData()

    val getSelectedProductName: LiveData<String> = selectedProductName

    private val selectedProductId: MutableLiveData<String> = MutableLiveData()


    val getStoreProductList: LiveData<ArrayList<StoreProduct>> = storeProductList

    fun getSelectItem(): LiveData<Boolean> {
        return selectItem
    }

    //not used yet
    fun selectProduct() {
        selectItem.value = true
        Log.e(TAG, "select a product")
    }

    fun getproducts(view: View) {
        repo.getStoreProducts(object : MyCallback {
            override fun onCallBack(listOfModel: ArrayList<StoreProduct>) {
                storeProductList.value = listOfModel
                Log.e(TAG, "Test here it can't open alert dialog")
                val SelectProductDialog =
                    ProductDialog(
                        view.context,
                        listOfModel,
                        object : SelectOrCancelProductListener {
                            override fun onProductSelected(selectedProduct: StoreProduct) {
                                //get selected Product
                                selectedProductName.value = selectedProduct.product_name
                                selectedProductId.value = selectedProduct.product_id
                                Log.e(TAG, "Selected Product is " + selectedProduct.product_name)
                            }

                            override fun onProductCanceled() {
                                Log.e(TAG, "Dialog cancel")
                            }

                        })
                SelectProductDialog.show()
            }
        })
    }


}