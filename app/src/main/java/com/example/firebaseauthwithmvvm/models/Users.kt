package com.example.firebaseauthwithmvvm.models

class Users {

    var id: String = ""
    var email: String = ""
    var password: String = ""

    constructor(
        id: String,
        email: String,
        password: String
    ) {
        this.id = id
        this.email = email
        this.password = password
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