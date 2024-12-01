// Person.kt
package com.example.ordercoffee.data

import java.time.LocalDateTime

data class Person(
    val name: String,
    val phone: String,
    val email: String,
    val address: String,
    val points: Int,
    val ListOnGoing: List<Order> = emptyList(),
    val ListHistory: List<Order> = emptyList(),
    val ListCart: List<ItemCart> = emptyList(),
    val NowItemCart: ItemCart
)
