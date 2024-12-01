package com.example.ordercoffee.data

import androidx.annotation.DrawableRes
import com.example.ordercoffee.R

data class CoffeeProduct(
    val id: Int,
    val name: String,
    val price: Int,
    @DrawableRes val imageResId: Int
)



