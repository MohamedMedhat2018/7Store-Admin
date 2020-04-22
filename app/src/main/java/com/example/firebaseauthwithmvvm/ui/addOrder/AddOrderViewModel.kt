package com.example.firebaseauthwithmvvm.ui.addOrder

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.firebaseauthwithmvvm.R
import com.example.firebaseauthwithmvvm.data.repository.StoreProductRepo
import com.example.firebaseauthwithmvvm.models.Order
import com.example.firebaseauthwithmvvm.models.StoreProduct
import com.example.firebaseauthwithmvvm.ui.addItemToStore.AddingToStoreListener
import com.example.firebaseauthwithmvvm.ui.dialog.ProductDialog
import com.example.firebaseauthwithmvvm.ui.store.MyCallback
import com.example.firebaseauthwithmvvm.utils.Utils
import com.example.firebaseauthwithmvvm.utils.fire_utils.RefBase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlin.collections.ArrayList

class AddOrderViewModel(application: Application, private val repo: StoreProductRepo) :
    AndroidViewModel(application) {

    val TAG = AddOrderViewModel::class.java.simpleName
    val context = getApplication<Application>().applicationContext
    val app = application
    lateinit var order: Order

    var addOrderListener: AddingToStoreListener? = null

    private val disposables = CompositeDisposable()


    val customerName: MutableLiveData<String> = MutableLiveData()
    val customerPhone: MutableLiveData<String> = MutableLiveData()
    val customerAddress: MutableLiveData<String> = MutableLiveData()
    val customerAccount: MutableLiveData<String> = MutableLiveData()

    //list of products
    private val storeProductList: MutableLiveData<ArrayList<StoreProduct>> = MutableLiveData()
    val selectedProductName: MutableLiveData<String> = MutableLiveData()
    private val selectedProductId: MutableLiveData<String> = MutableLiveData()
    val selectedProductMaxQnt: MutableLiveData<Int> = MutableLiveData<Int>().apply { value = null }

    val productQNT = MutableLiveData<Int>().apply { value = 1 }
    val productQNTError = MutableLiveData<String>().apply { value = "" }
    val customerRequestDate: MutableLiveData<String> = MutableLiveData()
    val customerDeliveryDate: MutableLiveData<String> = MutableLiveData()

//    private lateinit var picker: DatePickerDialog

    //NU
    private val selectItem: MutableLiveData<Boolean> = MutableLiveData()
    fun getSelectItem(): LiveData<Boolean> {
        return selectItem
    }

    //not used yet
    fun selectProduct() {
        selectItem.value = true
        Log.e(TAG, "select a product")
    }

    fun clearProductQNTError() {
        productQNTError.value = ""
    }

    fun getproducts(view: View) {
        addOrderListener?.onStarted()
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
                                selectedProductMaxQnt.value =
                                    selectedProduct.product_quantity.toInt()
                                productQNT.value = 1
                                productQNTError.value = ""
//                                productQNT.value?.rangeTo(selectedProductMaxQnt.value!!)
                                Log.e(
                                    TAG,
                                    "Selected Product is " + selectedProduct.product_name
                                )
                            }

                            override fun onProductCanceled() {
                                Log.e(TAG, "Dialog cancel")
                            }
                        })

//                    SelectProductDialog!!.show()

                if (SelectProductDialog.preventShowDialogTwice.equals(false) && !SelectProductDialog.isShowing) {
                    addOrderListener?.onSuccess("")
                    SelectProductDialog.show()
                    SelectProductDialog.preventShowDialogTwice = true
                }
            }
        })
    }

    fun incrementNProducts() {
        Log.e(TAG, "here 1")
        if (selectedProductMaxQnt.value != null) {
            Log.e(TAG, "here 2 ${productQNT.value} and ${selectedProductMaxQnt.value}")
            if (selectedProductMaxQnt.value!! > productQNT.value!!) {
                Log.e(TAG, "here 3")
                productQNT.value.let { a ->
                    productQNT.value = a?.plus(1)
                    productQNTError.value = ""
                }
            } else {
                productQNTError.value = "Max quantity is ${selectedProductMaxQnt.value} "
            }
        }
    }

    fun decrementNProducts() {
        if (selectedProductMaxQnt.value != null) {
            Log.e(TAG, "here 2 ${productQNT.value} and ${selectedProductMaxQnt.value}")
            if (productQNT.value!! > 1) {
                Log.e(TAG, "here 3")
                productQNT.value.let { a ->
                    productQNT.value = a?.minus(1)
                    productQNTError.value = ""
                }
            } else {
                productQNTError.value = "min quantity is ${productQNT.value} "
            }
        }
    }

    fun addRequestDate(view: View) {
        Utils().createDateDialog(view, customerRequestDate)
    }

    fun addDeleverDate(view: View) {
        Utils().createDateDialog(view, customerDeliveryDate)
    }

    fun confirmOrder() {

        if (checkFields()) {
            addOrderListener?.onStarted()
            Log.e("AddOrderActivity", "on click")
            addOrderToStore()
        }
    }

    private fun addOrderToStore() {
        val orderId = RefBase.refOrder().push().key
        order = Order(
            orderId,
            customerName.value,
            customerPhone.value,
            customerAddress.value,
            customerAccount.value,
            selectedProductId.value,
            productQNT.value.toString(),
            customerRequestDate.value,
            customerDeliveryDate.value
        )

        val disposable = repo.addNewOrder(order)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                addOrderListener?.onSuccess("Order Add successfully")
            }, {
                addOrderListener?.onFailure(it.message)
            })
        disposables.add(disposable)
    }

    fun checkFields(): Boolean {
        when {
            customerName.value?.trim().isNullOrEmpty() -> {
                Log.e(TAG, "name")
                addOrderListener?.onFailure(app.resources.getString(R.string.enter_customer_name))
                return false
            }
            customerPhone.value?.trim().isNullOrEmpty() -> {
                addOrderListener?.onFailure(app.resources.getString(R.string.enter_customer_phone))
                Log.e(TAG, "phone")
                return false
            }
            customerAddress.value.isNullOrBlank() -> {
                addOrderListener?.onFailure(app.resources.getString(R.string.enter_customer_address))
                return false
            }
            customerAccount.value.isNullOrBlank() -> {
                addOrderListener?.onFailure(app.resources.getString(R.string.enter_customer_account))
                return false
            }
            selectedProductName.value.isNullOrBlank() -> {
                addOrderListener?.onFailure(app.resources.getString(R.string.choose_a_product))
                return false
            }
            productQNT.value.toString().isNullOrBlank() -> {
                addOrderListener?.onFailure(app.resources.getString(R.string.choose_product_quantity))
            }
            customerRequestDate.value.isNullOrBlank() -> {
                addOrderListener?.onFailure(app.resources.getString(R.string.enter_request_date))
                return false
            }
            customerDeliveryDate.value.isNullOrBlank() -> {
                addOrderListener?.onFailure(app.resources.getString(R.string.enter_delivery_date))
                return false
            }
        }
        return true
    }

    fun cancelOrder(view: View) {
        addOrderListener?.onSuccess("")
    }
}