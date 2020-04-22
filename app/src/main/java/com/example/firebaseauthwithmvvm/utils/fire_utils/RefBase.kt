package com.example.firebaseauthwithmvvm.utils.fire_utils

import com.example.firebaseauthwithmvvm.constants.Childs
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

object RefBase {

    fun root(): DatabaseReference {
        return FirebaseDatabase.getInstance().getReference()
    }

    fun refStoreProduct(customer: String): DatabaseReference {
        return root().child(Childs.StoreProducts.name).child(customer)
    }

//        fun refStoreProduct(): DatabaseReference {
//            return root().child(Childs.StoreProducts.name)
//        }

    var refRoot: DatabaseReference = FirebaseDatabase.getInstance().reference;

    fun refStoreProduct() = refRoot.child(Childs.PRODUCTS.name)

    fun refUser() = refRoot.child(Childs.USERS.name)

    fun refOrder() = refRoot.child(Childs.ORDERS.name)

    //    public static DatabaseReference requestPending() {
//        return root().child(Constants.RQUEST_PENDING);
//    }
}