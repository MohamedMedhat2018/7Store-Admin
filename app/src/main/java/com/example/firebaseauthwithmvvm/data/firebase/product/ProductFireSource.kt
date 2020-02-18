package com.example.firebaseauthwithmvvm.data.firebase.product

import android.util.Log
import com.example.firebaseauthwithmvvm.models.Product
import com.example.firebaseauthwithmvvm.ref_base.RefBase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import io.reactivex.Completable

class ProductFireSource {


    private val TAG: String = Product::javaClass.name

    //    private val mAuth: FirebaseAuth by lazy {
////        mAuth = FirebaseAuth.getInstance()
//        FirebaseAuth.getInstance()
//    }
//    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance();
//    private val data: FirebaseAuth = FirebaseAuth.getInstance();

    public fun addNewOrder(product: Product) = Completable.create { emitter ->
        RefBase.refStoreProduct().setValue(product)
            .addOnSuccessListener {
                if (!emitter.isDisposed) {
                    emitter.onComplete()
                }
            }
            .addOnFailureListener {
                Log.e(TAG, it.message)
                if (!emitter.isDisposed) {
                    emitter.onError(it)
                }
            }
//            .addOnCompleteListener {
//
//            }


    }

}