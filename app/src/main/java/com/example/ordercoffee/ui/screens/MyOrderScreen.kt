package com.example.ordercoffee.ui.screens

import android.content.ClipData
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import java.time.LocalDateTime



@Composable
fun MyOrderScreen(navController: NavController) {
    val personState = PersonObject.current
    val onGoingOrders = remember { mutableStateListOf<Order>().apply { addAll(personState.value.ListOnGoing) } }
    var historyOrders by remember { mutableStateOf(personState.value.ListHistory) }
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
                .padding(25.dp)
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        ) {
            // Tab Row
            Text(
                "My Order",
                modifier = Modifier.wrapContentHeight(Alignment.Top),
                style = LocalTextStyle.current.copy(
                    color = Color(0xFF78B3CE),
                    textAlign = TextAlign.Center,
                    fontSize = 25.sp
                ),
                fontWeight = FontWeight.Bold
            )
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


                OnGoingList(onGoingOrders,
                    onOrderReceived = { order ->
                        // Xóa đơn hàng khỏi danh sách OnGoing
                        onGoingOrders.remove(order)
                        // Thêm đơn hàng vào danh sách History
                        personState.value = personState.value.copy(ListOnGoing = onGoingOrders)
                        historyOrders = historyOrders + order
                        personState.value = personState.value.copy(ListHistory = historyOrders)
                        // Chuyển sang tab "History"
                        selectedTabIndex = 1
                    })
            } else {
                HistoryList(historyOrders)
            }
        }
    }
}

@Composable
fun OnGoingList(orders: List<Order>, onOrderReceived: (Order) -> Unit) {
    val formatter = DateTimeFormatter.ofPattern("dd MMMM | hh:mm a", Locale.ENGLISH)

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
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
                Column(
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = "$${order.itemCart.TotalPrice}",
                        fontSize = 22.sp,
                        color = Color.Black
                    )
                    Row(
                        modifier = Modifier
                            .padding(top = 15.dp)
                            .clip(RoundedCornerShape(15.dp))
                            .size(100.dp, 30.dp)
                            .background(Color(0xFF78B3CE))
                            .clickable {
                                onOrderReceived(order)
                                // Xử lý khi nhấn nút "Received"
                            },
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Received",
                            style = LocalTextStyle.current.copy(
                                color = Color.White,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }
            }
            Divider(color = Color.LightGray, thickness = 1.dp)
        }
    }
}

@Composable
fun HistoryList(orders: List<Order>) {
    val formatter = DateTimeFormatter.ofPattern("dd MMMM | hh:mm a", Locale.ENGLISH)

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
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
                Column(
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = "$${order.itemCart.TotalPrice}",
                        fontSize = 22.sp,
                        color = Color.Black
                    )
                    // Không có nút "Received" trong HistoryList
                }
            }
            Divider(color = Color.LightGray, thickness = 1.dp)
        }
    }
}



