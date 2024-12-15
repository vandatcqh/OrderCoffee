// RewardScreen.kt
package com.example.ordercoffee.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ordercoffee.R
import com.example.ordercoffee.data.Person
import com.example.ordercoffee.ui.components.BottomNavigation
import com.example.ordercoffee.LoyaltyCard
import java.time.format.DateTimeFormatter
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.font.FontWeight
import com.example.ordercoffee.data.CoffeeDataSource.PersonObject
import com.example.ordercoffee.data.Order

// Giả sử bạn đã định nghĩa LocalPerson như sau:
//val LocalPerson = staticCompositionLocalOf<Person> { error("No Person provided") }

@Composable
fun RewardScreen(navController: NavController) {
    // Truy cập dữ liệu Person từ CompositionLocal
    val person = PersonObject.current

    // Tính tổng điểm từ ListHistory
    val totalPoints = person.value.numPoint

    Scaffold(
        containerColor = Color.White,
        bottomBar = {
            BottomAppBar(
                containerColor = Color.White,
                contentPadding = PaddingValues(horizontal = 25.dp, vertical = 0.dp)
            ) {
                BottomNavigation(navController, "reward")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(start = 25.dp, top = 25.dp, end = 25.dp, bottom = 0.dp)
                .fillMaxWidth()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        ) {
            Text(
                "Rewards",
                modifier = Modifier.wrapContentHeight(Alignment.Top),
                style = LocalTextStyle.current.copy(
                    color = Color(0xFF78B3CE),
                    textAlign = TextAlign.Center,
                    fontSize = 25.sp
                ),
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(26.dp))
            LoyaltyCard(person.value.numLoyalty)
            Spacer(modifier = Modifier.height(15.dp))
            PointCard(navController, totalPoints)
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                "History Rewards",
                modifier = Modifier
                    .align(Alignment.Start),
                style = LocalTextStyle.current.copy(
                    color = Color(0xFF78B3CE),
                    textAlign = TextAlign.Left,
                    fontSize = 14.sp
                ),
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(24.dp))
            // Sử dụng LazyColumn để hiển thị danh sách lịch sử
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                items(person.value.ListHistory) { order ->
                    OrderRewardItem(order)
                    Spacer(modifier = Modifier.height(20.dp))
                    Divider(
                        color = Color.LightGray,
                        thickness = 1.dp,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

@Composable
fun PointCard(navController: NavController, points: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0.2f, 0.29f, 0.35f, 1.0f))
    )
    {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 30.dp,
                    top = 26.dp,
                    end = 23.dp,
                    bottom = 24.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(
                modifier = Modifier.size(71.dp, 58.dp),
                verticalArrangement = Arrangement.SpaceBetween
            )
            {
                Text(
                    "My Points:",
                    style = LocalTextStyle.current.copy(
                        color = Color(0.85f, 0.85f, 0.85f, 1.0f),
                        fontSize = 14.sp,
                    )
                )
                Text(
                    text = "$points",
                    style = LocalTextStyle.current.copy(
                        color = Color(0.85f, 0.85f, 0.85f, 1.0f),
                        fontSize = 24.sp
                    )
                )
            }
            Row(
                modifier = Modifier
                    .padding(vertical = 15.dp)
                    .size(93.dp, 28.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(Color(red = 0.635f, green = 0.804f, blue = 0.914f, alpha = 0.19f))
                    .clickable {
                        navController.navigate("redeem_screen")
                    },
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Text(
                    text = "Redeem drinks",
                    style = LocalTextStyle.current.copy(
                        color = Color(0.85f, 0.85f, 0.85f, 1.0f),
                        fontSize = 10.sp
                    )
                )
            }
        }
    }
}

@Composable
fun OrderRewardItem(order: Order) {
    val formatter = DateTimeFormatter.ofPattern("dd MMMM | hh:mm a")
    val formattedDateTime = order.orderTime.format(formatter)

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(end = 16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = order.itemCart.coffeeProduct.name,
                style = LocalTextStyle.current.copy(
                    color = Color.Black,
                    textAlign = TextAlign.Left,
                    fontSize = 12.sp
                ),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = formattedDateTime,
                style = LocalTextStyle.current.copy(
                    color = Color.Black,
                    textAlign = TextAlign.Left,
                    fontSize = 10.sp
                )
            )
        }
        Text(
            text = "+ ${order.itemCart.TotalPoint} Pts",
            style = LocalTextStyle.current.copy(
                color = Color.Black,
                textAlign = TextAlign.Left,
                fontSize = 16.sp
            )
        )
    }
}

@Composable
fun ItemReward(A: String, B: String) {
    // Bạn có thể loại bỏ hàm này nếu không cần sử dụng nữa
}
