package com.example.ordercoffee.data

import java.time.LocalDateTime

data class Order(
    val itemCart: ItemCart,
    val orderTime: LocalDateTime
)