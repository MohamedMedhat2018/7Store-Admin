package com.example.firebaseauthwithmvvm.ref_base

import com.example.firebaseauthwithmvvm.constants.Childs
import com.example.firebaseauthwithmvvm.constants.Constants
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

object RefBase {

    //    private var root: DatabaseReference = FirebaseDatabase
//        .getInstance().reference;
//    lateinit var root: DatabaseReference;
    var refRoot: DatabaseReference;


    //In Kotlin, we need to use the object keyword to
    // use Singleton class. The object class can
    // have functions, properties, and the init method.
    // The constructor method is not allowed in an object
    // so we can use the init method if some initialization
    // is required and the object can be defined inside
    // a class. The object gets instantiated when it is
    // used for the first time.
    init {
        refRoot = FirebaseDatabase.getInstance().reference
    }

    fun refStoreProduct() = refRoot.child(Childs.PRODUCTS.name)



}