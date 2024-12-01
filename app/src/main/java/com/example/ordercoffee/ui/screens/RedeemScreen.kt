package com.example.ordercoffee.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ordercoffee.LoyaltyCard
import com.example.ordercoffee.R
import com.example.ordercoffee.ui.components.BottomNavigation

@Preview(widthDp = 375, heightDp = 812)
@Composable
fun RewardScreenPreview()
{
    RedeemScreen()
}


@Composable
fun RedeemScreen()
{
    Column(
        modifier = Modifier
            .padding(start = 25.dp, end = 25.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    )
    {
        ItemRedeem()
        ItemRedeem()
        ItemRedeem()
    }
}

@Preview(widthDp = 312, heightDp = 61)
@Composable
fun ItemRedeemPreview()
{
    ItemRedeem()
}


@Composable
fun ItemRedeem() {
    Row(
        modifier = Modifier
            .height(60.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    ) {
        // Image
        Image(
            painter = painterResource(R.drawable.coffee_img_01),
            contentDescription = "Coffee Image",
            modifier = Modifier
                .size(80.dp, 60.dp) // Kích thước của mỗi hình ảnh
        )

        // Column chứa 2 dòng Text
        Column(
            modifier = Modifier
                .padding(top = 15.dp, bottom = 13.dp)
                .size(150.dp, 41.dp)
        ) {
            Text(
                text = "Cafe Latte",
                fontSize = 14.sp
            )
            Text(
                text = "Valid Until 202020",
                fontSize = 10.sp
            )
        }

        // Row chứa điểm
        Row(
            modifier = Modifier
                .padding(top = 12.dp, bottom = 17.dp)
                .clip(RoundedCornerShape(50)) // Bo góc
                .background(Color(0xFF324A59)) // Màu nền
                .fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "1340 pts",
                color = Color.White,
                fontSize = 12.sp
            )
        }
    }
}
