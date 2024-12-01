package com.example.ordercoffee.data


data class ItemCart(
    val coffeeProduct: CoffeeProduct,
    val quantity : Int,
    val TotalPrice: Int,
    val TotalPoint: Int,
    val shot: String, // "Single", "Double"
    val select: String, // "DrinkIn", "TakeAway"
    val size: String, // "S", "M", "L"
    val iceLevel: String, // "No", "Less", "Normal"
)