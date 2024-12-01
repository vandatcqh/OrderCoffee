package com.example.ordercoffee.ui.screens

import android.content.ClipData
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ordercoffee.R
import androidx.navigation.NavController
import com.example.ordercoffee.ui.components.BottomNavigation
import androidx.compose.runtime.*
import com.example.ordercoffee.data.CoffeeDataSource.PersonObject
import com.example.ordercoffee.data.CoffeeDataSource.sampleList
import com.example.ordercoffee.data.Order
import java.time.format.DateTimeFormatter
import java.util.Locale
import androidx.compose.foundation.lazy.items



@Composable
fun MyOrderScreen(navController: NavController) {
    Scaffold(

        containerColor = Color.White,
        bottomBar = {
            BottomAppBar(
                containerColor = Color.White,
                contentPadding = PaddingValues(horizontal = 25.dp, vertical = 0.dp)
            )
            {
                BottomNavigation(navController, "myorder")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(top = 30.dp)
                .padding(horizontal = 25.dp)
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        ) {
            // Tab Row
            Text("My Order", Modifier.wrapContentHeight(Alignment.Top), style = LocalTextStyle.current.copy(color = Color(0.0f, 0.09f, 0.2f, 1.0f), textAlign = TextAlign.Center, fontSize = 25.0.sp))
            val tabs = listOf("On going", "History")
            var selectedTabIndex by remember { mutableStateOf(0) }
            TabRow(
                selectedTabIndex = selectedTabIndex,
                containerColor = Color.White,
                contentColor = Color.Black
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        text = { Text(title) }
                    )
                }
            }

            // List of Orders
            if (selectedTabIndex == 0) {
                val personState = PersonObject.current
                val onGoingOrders = personState.value.ListOnGoing
                OrderList(onGoingOrders)
            } else {
                OrderList(historyOrders)
            }
        }
    }
}

@Composable
fun OrderList(orders: List<Order>) {
    val formatter = DateTimeFormatter.ofPattern("dd MMMM | hh:mm a", Locale.ENGLISH)

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp) // Thêm padding nếu cần
    ) {
        items(orders) { order ->
            val formattedDateTime = order.orderTime.format(formatter)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = formattedDateTime,
                        fontSize = 16.sp,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(R.drawable.cup_in_myorder),
                            contentDescription = "Cup In MyOrder",
                            modifier = Modifier
                                .size(14.dp)
                                .padding(end = 3.dp)
                        )
                        Text(
                            text = order.itemCart.coffeeProduct.name,
                            fontSize = 16.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(R.drawable.locate_in_myorder),
                            contentDescription = "Locate In MyOrder",
                            modifier = Modifier
                                .size(14.dp)
                                .padding(end = 3.dp)
                        )
                        Text(
                            text = order.itemCart.select, // "DrinkIn" hoặc "TakeAway"
                            fontSize = 16.sp
                        )
                    }
                }
                Text(
                    text = "$${order.itemCart.TotalPrice}",
                    fontSize = 22.sp,
                    color = Color.Black
                )
            }
            Divider(color = Color.LightGray, thickness = 1.dp)
        }
    }
}



val historyOrders = sampleList





