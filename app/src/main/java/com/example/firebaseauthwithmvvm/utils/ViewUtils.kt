package com.example.firebaseauthwithmvvm.utils

import android.content.Context
import android.content.Intent
import com.example.firebaseauthwithmvvm.ui.add_product.addProductActivity
import com.example.firebaseauthwithmvvm.ui.auth.LoginActivity
import com.example.firebaseauthwithmvvm.ui.home2.Main2Activity

fun Context.startHomeActivty() =
    Intent(this, Main2Activity::class.java).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }

//use when u want to logout
fun Context.startLoginActivity() =
    Intent(this, LoginActivity::class.java).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }

fun Context.startAddProduct() =
    Intent(this, addProductActivity::class.java).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)

    }
