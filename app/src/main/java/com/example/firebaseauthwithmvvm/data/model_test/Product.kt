package com.example.firebaseauthwithmvvm.data.model_test

import java.io.Serializable

class Product(product_name: String, product_qnt: Int, product_img: String) : Serializable {

    var productName: String = product_name
    var productQnt: Int = product_qnt
    var productImg: String = product_img
}