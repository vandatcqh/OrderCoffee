package com.example.ordercoffee.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ordercoffee.R
import com.example.ordercoffee.data.CoffeeProduct
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.layout.ContentScale
import androidx.navigation.NavController
import com.example.ordercoffee.LoyaltyCard
import com.example.ordercoffee.data.ItemCart
import com.example.ordercoffee.data.CoffeeDataSource.PersonObject
import com.example.ordercoffee.data.Order
import java.time.LocalDateTime


@Composable
fun MyCartScreen(navController: NavController) {
    val personState = PersonObject.current
    val listCart = personState.value.ListCart
    val totalPrice = listCart.sumOf { it.TotalPrice }

    Scaffold(
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF78B3CE))
            ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Tổng tiền",
                            style = LocalTextStyle.current.copy(
                                color = Color.White,
                                fontSize = 16.sp
                            )
                        )
                        Text(
                            text = "$$totalPrice",
                            style = LocalTextStyle.current.copy(
                                color = Color.White,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(15.dp))
                            .background(Color.White)
                            .clickable {
                                val ListBuy = personState.value.ListCart
                                val newOrders = ListBuy.map { itemCart ->
                                    Order(
                                        itemCart = itemCart,
                                        orderTime = LocalDateTime.now() // Gán thời gian hiện tại
                                    )
                                }
                                personState.value = personState.value.copy(ListCart = emptyList())
                                personState.value = personState.value.copy(ListOnGoing = personState.value.ListOnGoing + newOrders)

                                navController.navigate("ordersuccess_screen")
                            }
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.iconly_light_buy),
                            contentDescription = "Checkout Icon",
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Thanh toán",
                            style = LocalTextStyle.current.copy(
                                color = Color(0xFF78B3CE),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(top = 57.dp)
                .padding(horizontal = 25.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            Image(
                painter = painterResource(id = R.drawable.icon_arrow_left),
                contentDescription = "Quay lại",
                modifier = Modifier
                    .size(30.dp)
                    .clickable { navController.popBackStack() }
            )
            Text("Giỏ hàng của tôi", fontSize = 25.sp)
            Spacer(modifier = Modifier.height(20.dp))
            listCart.forEach { itemCart ->
                CartItem(itemCart)
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

@Composable
fun CartItem(itemCart: ItemCart) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp))
            .background(Color(0xFFD4EBF8)),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(itemCart.coffeeProduct.imageResId),
                contentDescription = itemCart.coffeeProduct.name,
                modifier = Modifier.size(90.dp, 70.dp)
            )
            Column {
                Text(itemCart.coffeeProduct.name, fontSize = 16.sp)
                Text("Số lượng: ${itemCart.quantity}", fontSize = 16.sp)
                Text(
                    "Tùy chọn: ${itemCart.shot}, ${itemCart.select}, ${itemCart.size}, ${itemCart.iceLevel}",
                    fontSize = 12.sp
                )
            }
        }
        Row(
            modifier = Modifier.padding(end = 15.dp)
        ) {
            val x = itemCart.TotalPrice
            Text("$$x", fontSize = 20.sp)
        }
    }
}

@Composable
fun DeleteButton(modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(15.dp))
            .background(Color(0xFFFFE5E5)), // Thêm sự kiện click
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.iconly_light_outline_delete),
            contentDescription = "Remove Button",
            modifier = Modifier.size(24.dp)
        )
    }
}

