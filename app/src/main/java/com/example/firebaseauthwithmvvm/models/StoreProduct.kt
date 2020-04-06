package com.example.firebaseauthwithmvvm.models

import java.io.Serializable

class StoreProduct {

    var product_image: String = ""
    var product_name: String = "d"
    var product_quantity: String = ""
    var product_material_Cost: String = ""
    var product_price: String = ""

    constructor(
        product_image: String,
        product_name: String,
        product_quantity: String,
        product_material_Cost: String,
        product_price: String
    ) {
        this.product_image = product_image
        this.product_name = product_name
        this.product_quantity = product_quantity
        this.product_material_Cost = product_material_Cost
        this.product_price = product_price
    }

    constructor() {}

/*    private var product_image: String = product_image
        get() {
            return field
        }
        set(value) {
            field = value
        }
    private var product_name: String = product_name
        get() {
            return field
        }
        set(value) {
            field = value
        }
    private var product_quantity: String = product_quantity
        get() {
            return field
        }
        set(value) {
            field = value
        }
    private var product_material_Cost: String = product_material_Cost
        get() {
            return field
        }
        set(value) {
            field = value
        }
    private var product_price: String = product_price
        get() {
            return field
        }
        set(value) {
            field = value
        }*/
}