package com.example.firebaseauthwithmvvm.utils.fire_utils

import com.example.firebaseauthwithmvvm.constants.Constants
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RefBase {


    companion object {
        fun root(): DatabaseReference {
            return FirebaseDatabase.getInstance().getReference()
        }

        fun refStoreProduct(customer: String): DatabaseReference {
            return root().child(Constants.STORE_PRODUCTS).child(customer)
        }

        fun refStoreProduct(): DatabaseReference {
            return root().child(Constants.STORE_PRODUCTS)
        }

    }


    //    public static DatabaseReference requestPending() {
//        return root().child(Constants.RQUEST_PENDING);
//    }
}