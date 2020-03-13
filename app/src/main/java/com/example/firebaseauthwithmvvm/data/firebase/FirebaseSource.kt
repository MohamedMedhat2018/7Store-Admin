package com.example.firebaseauthwithmvvm.data.firebase

import android.util.Log
import com.example.firebaseauthwithmvvm.constants.Constants
import com.example.firebaseauthwithmvvm.models.StoreProduct
import com.example.firebaseauthwithmvvm.ref_base.RefBase
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener
import com.pixplicity.easyprefs.library.Prefs
import io.reactivex.Completable
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.log

class FirebaseSource {

    private val TAG = FirebaseSource::class.java.canonicalName

    private val firebaseAuth: FirebaseAuth by lazy {
        Log.e(TAG, "FirebaseSoure " + FirebaseAuth.getInstance())
        FirebaseAuth.getInstance()
    }

    /* Completable:
    * {@code Completable} behaves similarly to {@link Observable} except that it can only emit either
    * a completion or error signal (there is no {@code onNext} or {@code onSuccess} as with the other
    * reactive types).
*/
    fun login(email: String, pass: String) = Completable.create { emitter ->
        firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
            if (!emitter.isDisposed) {
                if (it.isSuccessful)
                    emitter.onComplete()
                else
                    emitter.onError(it.exception!!)
            } else {
                Log.e(TAG, "FirebaseSoure Not Register2222 ")
            }
        }
    }

    fun register(email: String, pass: String) = Completable.create { emitter ->
        Log.e(TAG, "FirebaseSoure Register999 ")
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener {
                Log.e(TAG, "FirebaseSoure Register Failed3.1 ")
                if (!emitter.isDisposed) {
                    Log.e(TAG, "FirebaseSoure Register1111 ")
                    if (it.isSuccessful) {
                        emitter.onComplete()
                        Prefs.edit().putString(
                            Constants.FIREBASE_UID,
                            FirebaseAuth.getInstance().currentUser?.uid
                        )
                        Log.e(
                            TAG,
                            "FirebaseSoure Register Successful " + FirebaseAuth.getInstance().currentUser?.uid
                        )

                    } else {
                        Log.e(TAG, "FirebaseSoure Register Failed ")
                        emitter.onError(it.exception!!)
                    }
                } else {
//                    Log.e(TAG, "FirebaseSoure Register error 2 " + emitter.onError(it.exception!!))
                }
            }.addOnFailureListener {
                Log.e(TAG, "FirebaseSoure Register Failed3 ")

            }.addOnSuccessListener {
                Log.e(TAG, "FirebaseSoure Register Failed3.2 ")

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

    var modelList = ArrayList<StoreProduct>()
    var modelList2 = ArrayList<StoreProduct>()

    fun getStoreProducts(): ArrayList<StoreProduct> {
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
                    modelList2 = modelList
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
            return ArrayList<StoreProduct>()
        }

        Log.e(TAG, "get data size2  ${modelList.size} ")

        return modelList2


    }


    fun logout() = firebaseAuth.signOut()

    fun currentUser() = firebaseAuth.currentUser


}