package com.example.firebaseauthwithmvvm.data.firebase

import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.example.firebaseauthwithmvvm.constants.Childs
import com.example.firebaseauthwithmvvm.constants.Constants
import com.example.firebaseauthwithmvvm.models.StoreProduct
import com.example.firebaseauthwithmvvm.models.Users
import com.example.firebaseauthwithmvvm.ref_base.RefBase
import com.example.firebaseauthwithmvvm.ui.store.MyCallback
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener
import com.pixplicity.easyprefs.library.Prefs
import io.reactivex.Completable

class FirebaseSource {

    private val TAG = FirebaseSource::class.java.canonicalName

    private val firebaseAuth: FirebaseAuth by lazy {
        Log.e(TAG, "FirebaseSource " + FirebaseAuth.getInstance())
        FirebaseAuth.getInstance()
    }

    /* Completable:
    * {@code Completable} behaves similarly to {@link Observable} except that it can only emit either
    * a completion or error signal (there is no {@code onNext} or {@code onSuccess} as with the other
    * reactive types).
*/
    fun login(email: String, pass: String) = Completable.create { emitter ->
        firebaseAuth.signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener {
                if (!emitter.isDisposed) {
                    if (it.isSuccessful)
                        emitter.onComplete()
                    else
                        emitter.onError(it.exception!!)
                } else {
                    Log.e(TAG, "FirebaseSource Not Register2222 ")
                }
            }
            .addOnSuccessListener {
                RefBase.getUser().child(Childs.email.name).equalTo(email).addValueEventListener(
                    object : ValueEventListener {
                        override fun onCancelled(p0: DatabaseError) {

                        }

                        override fun onDataChange(p0: DataSnapshot) {
                            if (p0.exists() && p0.childrenCount > 0){
                                p0.children.forEach {
                                        val user: Users? = it.getValue(Users::class.java)
                                        if (TextUtils.equals(user?.password, pass)){
                                            Log.e(TAG, "Success login ")
                                            //action


                                        }
                                }

                            }
                        }

                    })

            }
    }

    fun register(email: String, pass: String) = Completable.create { emitter ->
        Log.e(TAG, "FirebaseSource Register999 ")
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener {
                Log.e(TAG, "FirebaseSource Register Failed3.1 ")

//            isDisposed: true if this resource has been disposed
                if (!emitter.isDisposed) {
//                    Log.e(TAG, "FirebaseSource Register1111 " + emitter.onError(it.exception!!))
//                    Log.e(TAG, "FirebaseSource Register1111 ")
                    if (it.isSuccessful) {
                        Log.e(TAG, "FirebaseSource Register1111 ")
                        emitter.onComplete()
                    } else {
                        if (it.exception != null) {
                            Log.e(TAG, "FirebaseSource Register Failed ex" + it.exception)
//                            emitter.onError(it.exception!!)
                        } else {
                            Log.e(TAG, "FirebaseSource Register Failed ex2")
                            emitter.onError(UnknownError())
                        }
                    }
                }
            }
            .addOnSuccessListener {
                Prefs.edit().putString(Constants.FIREBASE_UID, FirebaseAuth.getInstance().currentUser?.uid)
                val Uid = FirebaseAuth.getInstance().currentUser?.uid
                Log.e(TAG, "FirebaseSource Register Successful " + FirebaseAuth.getInstance().currentUser?.uid
                )
                if (Uid != null) {
                    val user: Users = Users(Uid, email, pass)
                    RefBase.addUser(email, pass).child(Uid).setValue(user)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Log.e(TAG, "User Add Successful ")
                            }
                        }
                }
            }
            .addOnFailureListener {
                Log.e(TAG, "FirebaseSource Register Failed3 " + it.message)
            }
    }

    fun addStoreProduct(product: StoreProduct) = Completable.create { emitter ->
        val key: String? = (RefBase.refStoreProduct().push().key)
        RefBase.refStoreProduct().child(key!!).setValue(product)
            .addOnCompleteListener {
                if (!emitter.isDisposed) {
                    emitter.onComplete()
                    if (it.isSuccessful) {
                        Log.e(TAG, "Product Add Successful ")
                    } else {
                        Log.e(TAG, "Product Add Failed ")

                    }
                } else {
                    Log.e(TAG, "Product Add Successful done ")
                }
            }.addOnFailureListener {
                emitter.onError(it)
            }
    }

    //not use yet
    fun UploadImageToFirebase(product: StoreProduct) = Completable.create { emitter ->
        val key: String? = (RefBase.refStoreProduct().push().key)
        RefBase.refStoreProduct().child(key!!).setValue(product)
            .addOnCompleteListener {
                if (!emitter.isDisposed) {
                    emitter.onComplete()
                    if (it.isSuccessful) {
                        Log.e(TAG, "Product Add Successful ")
                    } else {
                        Log.e(TAG, "Product Add Failed ")

                    }
                }
            }.addOnFailureListener {
                emitter.onError(it)
            }
    }

    //    val storeProductList: ArrayList<StoreProduct>
    //using FirebaseRecyclerOptions
    fun getStoreProduct(): FirebaseRecyclerOptions<StoreProduct> {

        val q: Query = RefBase.refRoot.child("PRODUCTS")

        val options: FirebaseRecyclerOptions<StoreProduct> =
            FirebaseRecyclerOptions.Builder<StoreProduct>()
                .setQuery(q, StoreProduct::class.java)
                .build()

        Log.e(TAG, "get data  ${options.snapshots} ")

        return options
    }


    var modelList2 = ArrayList<StoreProduct>()

    fun getStoreProducts(myCallback: MyCallback) {
        var modelList = ArrayList<StoreProduct>()
        val q: Query = RefBase.refRoot.child("PRODUCTS")
        val postListener = object : ValueEventListener {

            override fun onCancelled(p0: DatabaseError) {
                Log.e(TAG, "get dataaa  ${p0.message} ")
            }

            override fun onDataChange(p0: DataSnapshot) {
//                p0.ref.removeEventListener(this)
                if (p0.exists() && p0.childrenCount > 0) {
                    val children = p0.children
                    children.forEach {
                        if (it != null) {
                            Log.e(
                                TAG,
                                "get data size5  ${it.getValue(StoreProduct::class.java)!!} "
                            )
                            modelList.add(it.getValue(StoreProduct::class.java)!!)
                        }
                        Log.e(TAG, "get data size  ${modelList.size} ")
                    }
                    myCallback.onCallBack(modelList)
//                    modelList2 = modelList
//                    modelList2 = modelList
//                    for (product in modelList){
//                        Log.e(TAG, "Product name  = ${product.product_name}")
//                    }

                    /*   for (dataSnap: DataSnapshot in p0.children) {
                           val storeProduct: StoreProduct? =
                               dataSnap.getValue(StoreProduct::class.java)
                           if (storeProduct != null) {
                               Log.e(TAG, "get dataaaa  ${p0.childrenCount} ")
                               modelList.add(storeProduct)
                           }
                           Log.e(TAG, "get data size  ${modelList.size} ")
                       }*/
                    Log.e(TAG, "get data size4  ${modelList.size} ")
                }
            }
        }
        q.addListenerForSingleValueEvent(postListener)
        Log.e(TAG, "get data size7  ${modelList.size} ")

        if (modelList.isEmpty()) {
            Log.e(TAG, "get data size3  ${modelList.size} ")
//            return ArrayList<StoreProduct>()
        }

        Log.e(TAG, "get data size2  ${modelList.size} ")

//        return modelList2
//        Log.e(TAG, "get data size7  ${modelList.size} ")

        if (modelList.isEmpty()) {
            Log.e(TAG, "get data size3  ${modelList.size} ")
//            return ArrayList<StoreProduct>()
        }
//
        Log.e(TAG, "get data size2  ${modelList.size} ")

//        return modelList2
//        return modelList


    }


    fun logout() = firebaseAuth.signOut()

    fun currentUser() = firebaseAuth.currentUser


}