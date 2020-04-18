package com.example.firebaseauthwithmvvm.utils.fire_utils

import com.example.firebaseauthwithmvvm.constants.Childs
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RefBase {


    companion object {
        fun root(): DatabaseReference {
            return FirebaseDatabase.getInstance().getReference()
        }

        fun refStoreProduct(customer: String): DatabaseReference {
            return root().child(Childs.StoreProducts.name).child(customer)
        }

        fun refStoreProduct(): DatabaseReference {
            return root().child(Childs.StoreProducts.name)
        }

    }


    //    public static DatabaseReference requestPending() {
//        return root().child(Constants.RQUEST_PENDING);
//    }
}