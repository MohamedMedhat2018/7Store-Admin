package com.example.firebaseauthwithmvvm.data.firebase

import android.util.Log
import com.example.firebaseauthwithmvvm.constants.Constants
import com.example.firebaseauthwithmvvm.models.StoreProduct
import com.example.firebaseauthwithmvvm.ref_base.RefBase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.pixplicity.easyprefs.library.Prefs
import io.reactivex.Completable
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


    fun getStoreProduct() = Completable.create { emitter ->
        val postListener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                Log.e(TAG, "ERROR $p0")
            }

            override fun onDataChange(p0: DataSnapshot) {
                p0.ref.removeEventListener(this)
                if (p0.exists() && p0.childrenCount > 0) {
                    for (dataSnap: DataSnapshot in p0.children) {
//                        val storeProduct: StoreProduct? =
//                            dataSnap.getValue(StoreProduct::class.java)
                        Log.e(TAG, "get data  ${dataSnap.getValue(StoreProduct::class.java)} ")
                    }
                }
            }

        }
        RefBase.refStoreProduct().addValueEventListener(postListener)
    }


    fun logout() = firebaseAuth.signOut()

    fun currentUser() = firebaseAuth.currentUser


}