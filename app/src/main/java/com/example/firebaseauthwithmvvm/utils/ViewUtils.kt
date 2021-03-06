package com.example.firebaseauthwithmvvm.utils

import android.content.Context
import android.content.Intent
import com.example.firebaseauthwithmvvm.constants.Childs
import com.example.firebaseauthwithmvvm.ui.addItemToStore.AddStoreItemActivity
import com.example.firebaseauthwithmvvm.ui.addOrder.AddOrderActivity
import com.example.firebaseauthwithmvvm.ui.add_product.addProductActivity
import com.example.firebaseauthwithmvvm.ui.auth.LoginActivity
import com.example.firebaseauthwithmvvm.ui.home.MainActivity
import com.example.firebaseauthwithmvvm.ui.orders.OrderFragment

fun Context.startHomeActivty() =
    Intent(this, MainActivity::class.java).also {
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
    Intent(this, AddStoreItemActivity::class.java).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }

fun Context.startAddNewOrder() =
    Intent(this, AddOrderActivity::class.java).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }

fun Context.stratOrdersFragment() =
    Intent(this, MainActivity::class.java).also {
        it.putExtra(Childs.fragmentNumber.name, 2)
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }