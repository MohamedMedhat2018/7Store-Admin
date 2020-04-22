package com.example.firebaseauthwithmvvm.models

class Order {
    var order_id: String = ""
    var order_name: String = ""
    var customer_phone: String = ""
    var customer_address: String = ""
    var order_link: String = ""
    var product_id: String = ""
    var order_quantity: String = ""
    var order_date: String = ""
    var order_delivered_date: String = ""

    constructor(
        order_id: String?,
        order_name: String?,
        customer_phone: String?,
        customer_address: String?,
        order_link: String?,
        product_id: String?,
        order_quantity: String?,
        order_date: String?,
        order_delivered_date: String?
    ) {
        this.order_id = order_id!!
        this.order_name = order_name!!
        this.customer_phone = customer_phone!!
        this.customer_address = customer_address!!
        this.order_link = order_link!!
        this.product_id = product_id!!
        this.order_quantity = order_quantity!!
        this.order_date = order_date!!
        this.order_delivered_date = order_delivered_date!!
    }

    constructor() {}

/*    private var order_image: String = order_image
        get() {
            return field
        }
        set(value) {
            field = value
        }
    private var order_name: String = order_name
        get() {
            return field
        }
        set(value) {
            field = value
        }
    private var order_quantity: String = order_quantity
        get() {
            return field
        }
        set(value) {
            field = value
        }
    private var order_material_Cost: String = order_material_Cost
        get() {
            return field
        }
        set(value) {
            field = value
        }
    private var order_price: String = order_price
        get() {
            return field
        }
        set(value) {
            field = value
        }*/
}