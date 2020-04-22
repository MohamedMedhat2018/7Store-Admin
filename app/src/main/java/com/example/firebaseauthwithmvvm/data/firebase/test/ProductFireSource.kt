package com.example.firebaseauthwithmvvm.data.firebase.test

import com.example.firebaseauthwithmvvm.models.ProductTest
import com.example.firebaseauthwithmvvm.utils.fire_utils.RefBase
import io.reactivex.Completable

class ProductFireSource {


    private val TAG: String = ProductTest::javaClass.name

    //    private val mAuth: FirebaseAuth by lazy {
////        mAuth = FirebaseAuth.getInstance()
//        FirebaseAuth.getInstance()
//    }
//    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance();
//    private val data: FirebaseAuth = FirebaseAuth.getInstance();

    public fun addNewOrder(productTest: ProductTest) = Completable.create { emitter ->
        RefBase.refStoreProduct().setValue(productTest)
            .addOnSuccessListener {
                if (!emitter.isDisposed) {
                    emitter.onComplete()
                }
            }
            .addOnFailureListener {
                //                Log.e(TAG, it.message)
                if (!emitter.isDisposed) {
                    emitter.onError(it)
                }
            }
//            .addOnCompleteListener {
//
//            }


    }

}