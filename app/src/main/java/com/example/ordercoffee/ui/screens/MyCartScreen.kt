package com.example.ordercoffee.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import kotlinx.coroutines.*


@Composable
fun MyCartScreen(navController: NavController) {
    val personState = PersonObject.current
    val listCart = personState.value.ListCart
    val totalPrice = listCart.sumOf { it.TotalPrice }

    Scaffold(
        containerColor = Color.White,
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
                            text = "Total Price",
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
                                val addPoint = newOrders.sumOf { it.itemCart.TotalPoint }
                                val addLoyalty = newOrders.sumOf { it.itemCart.quantity }
                                personState.value = personState.value.copy(numPoint = personState.value.numPoint + addPoint, numLoyalty = personState.value.numLoyalty + addLoyalty)
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
                            text = "Checkout",
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
            Text(
                "My Cart",
                modifier = Modifier.padding(top = 20.dp),
                style = LocalTextStyle.current.copy(
                    color = Color(0xFF78B3CE),
                    fontSize = 25.sp
                ),
                fontWeight = FontWeight.Bold
            )
            LazyColumn(
                modifier = Modifier
                    .padding(paddingValues)
            ) {
                items(listCart) { itemCart ->
                    CartItem(itemCart, onDelete =
                    {
                        val updatedList = personState.value.ListCart.toMutableList().apply {
                            remove(itemCart)
                        }
                        personState.value = personState.value.copy(ListCart = updatedList)
                    })
                }
            }
        }
    }
}

@Composable
fun CartItem(itemCart: ItemCart, onDelete:() -> Unit) {
    val listState = rememberLazyListState()
    LazyRow(
        state = listState,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            Row(
                modifier = Modifier
                    .size(305.dp, 96.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .background(Color(0xFFD4EBF8)),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier
                        .padding(vertical = 12.dp)
                        .padding(end = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(itemCart.coffeeProduct.imageResId),
                        contentDescription = itemCart.coffeeProduct.name,
                        modifier = Modifier.size(80.dp, 60.dp)
                    )
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        )
                        {
                            Column {
                                Text(itemCart.coffeeProduct.name, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                                Text("x${itemCart.quantity}", fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
                            }
                            val x = itemCart.TotalPrice
                            Text("$$x", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
                        }
                        Text(
                            "${itemCart.shot}| ${itemCart.select}| ${itemCart.size}| ${itemCart.iceLevel}",
                            fontSize = 10.sp
                        )
                    }
                }

            }
        }
        item {
            Row(
                modifier = Modifier
                    .size(48.dp, 96.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .background(Color(0xFFFFE5E5))
                    .clickable {
                        onDelete()
                        CoroutineScope(Dispatchers.Main).launch {
                            listState.scrollToItem(0)
                        }
                               },
                // Thêm sự kiện click
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
    }

}


