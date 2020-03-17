package com.example.firebaseauthwithmvvm.ref_base

import com.example.firebaseauthwithmvvm.constants.Childs
import com.example.firebaseauthwithmvvm.constants.Constants
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

object RefBase {

    //    private var root: DatabaseReference = FirebaseDatabase
//        .getInstance().reference;
//    lateinit var root: DatabaseReference;
    var refRoot: DatabaseReference = FirebaseDatabase.getInstance().reference;


    fun refStoreProduct() = refRoot.child(Childs.PRODUCTS.name)

    fun addUser(email: String, pass: String) = refRoot.child(Childs.USERS.name)

    fun getUser() = refRoot.child(Childs.USERS.name)


}