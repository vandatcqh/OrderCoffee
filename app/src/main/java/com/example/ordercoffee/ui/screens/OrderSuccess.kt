package com.example.ordercoffee.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeCompilerApi
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ordercoffee.LoyaltyCard
import com.example.ordercoffee.ui.theme.OrderCoffeeTheme
import com.example.ordercoffee.R
import androidx.navigation.NavController
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import com.example.ordercoffee.data.CoffeeDataSource
import com.example.ordercoffee.ui.components.CoffeeItem
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.text.font.FontWeight
import com.example.ordercoffee.data.ItemCart


@Composable
fun OrderSuccess(navController: NavController) {
    Column(
        modifier = Modifier.padding(horizontal = 30.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(211.dp))
        Image(
            painter = painterResource(R.drawable.icon_order_success),
            contentDescription = "Order Success Icon",
            modifier = Modifier.size(177.dp)
        )
        Spacer(modifier = Modifier.height(32.dp))
        // Thêm các thành phần khác tại đây
        Text("Order Success",
            style = LocalTextStyle.current.copy(
                color = Color(0xFF88C273),
                textAlign = TextAlign.Center,
                fontSize = 25.0.sp),
                fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(22.dp))
        Text("Your order has been placed successfully.\nFor more details, go to my orders.", style = LocalTextStyle.current.copy(color = Color(0.67f, 0.67f, 0.67f, 1.0f), textAlign = TextAlign.Center, fontSize = 14.0.sp))
        Spacer(modifier = Modifier.height(81.dp))
        Button(
            onClick = {
                // Navigate to MyCartScreen
                navController.navigate("myorder_screen")
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF78B3CE),  // Màu nền của Button
                contentColor = Color.White,  // Màu chữ hoặc icon bên trong Button
                disabledContainerColor = Color.Gray,  // Màu nền khi Button bị vô hiệu hóa
                disabledContentColor = Color.DarkGray // Màu chữ khi Button bị vô hiệu hóa
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text(text = "Track My Order")
        }
    }
}






