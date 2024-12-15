package com.example.ordercoffee.data

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableStateOf
import com.example.ordercoffee.R
import java.time.LocalDateTime

object CoffeeDataSource {
    val coffeeList = listOf(
        CoffeeProduct(1, "Espresso", 30, R.drawable.coffee_img_01),
        CoffeeProduct(2, "Americano", 25, R.drawable.coffee_img_02),
        CoffeeProduct(3, "Latte", 28, R.drawable.coffee_img_03),
        CoffeeProduct(4, "Macchiato", 27, R.drawable.coffee_img_04),
        CoffeeProduct(5, "Mocha", 26, R.drawable.coffee_img_05),
        CoffeeProduct(6, "Cappuccino", 30, R.drawable.coffee_img_06),
        CoffeeProduct(7, "Flat White", 29, R.drawable.coffee_img_07),
        CoffeeProduct(8, "Affogato", 24, R.drawable.coffee_img_08),
    )
    val sampleCoffeeProduct = coffeeList[0]
    val sampleItemCart =
        ItemCart(sampleCoffeeProduct, 0, 0, 0, "Single", "Drink In", "Small", "No Ice")
    val sampleList = listOf(
        Order(
            itemCart = sampleItemCart,
            orderTime = LocalDateTime.of(2023, 11, 28, 10, 30)
        ),
        Order(
            itemCart = sampleItemCart,
            orderTime = LocalDateTime.of(2023, 11, 29, 14, 45)
        )
    )
    val samplePerson = Person(
        name = "Ngo Van Dat",
        phone = "0123456789",
        email = "nguyenvana@example.com",
        address = "123 Đường ABC, Quận 1, TP.HCM",
        numLoyalty = 0,
        numPoint = 0,
        ListOnGoing = emptyList(),
        ListHistory = emptyList(),
        ListCart = emptyList(),
        NowItemCart = sampleItemCart
    )
    var PersonObject = compositionLocalOf {
        mutableStateOf(samplePerson)
    }

}
