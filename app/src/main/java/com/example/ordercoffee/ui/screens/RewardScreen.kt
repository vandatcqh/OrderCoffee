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
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import com.example.ordercoffee.ui.components.BottomNavigation


@Composable
fun RewardScreen(navController: NavController)
{
    Scaffold(
        containerColor = Color.White,
        bottomBar = {
            BottomAppBar(
                containerColor = Color.White,
                contentPadding = PaddingValues(horizontal = 25.dp, vertical = 0.dp)
            )
            {
                BottomNavigation(navController, "reward")
            }
        }
    )
    {
        paddingValues ->
        Column(
            modifier = Modifier
                .padding(start = 25.dp, end = 25.dp, bottom = 0.dp)
                .fillMaxWidth()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        )
        {
            Spacer(modifier = Modifier.height(30.dp))
            Text("Rewards", Modifier.wrapContentHeight(Alignment.Top), style = LocalTextStyle.current.copy(color = Color(0.0f, 0.09f, 0.2f, 1.0f), textAlign = TextAlign.Center, fontSize = 25.0.sp))
            Spacer(modifier = Modifier.height(26.dp))
            LoyaltyCard(5)
            Spacer(modifier = Modifier.height(15.dp))
            PointCard(2750)
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                "History Rewards",
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 6.dp),
                style = LocalTextStyle.current.copy(
                    color = Color(0.2f, 0.29f, 0.35f, 1.0f),
                    textAlign = TextAlign.Left,
                    fontSize = 14.0.sp
                )
            )
            Spacer(modifier = Modifier.height(24.dp))
            Column(
                modifier = Modifier
                    .padding(start = 6.dp, end = 9.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Top,
            )
            {
                ItemReward("Americano", "24 June | 12:30 PM")
                Spacer(modifier = Modifier.height(20.dp))
                Box(
                    modifier = Modifier.fillMaxWidth()
                )
                {
                    Image(
                        painter = painterResource(R.drawable.icon_line),
                        contentDescription = "Icon Line",
                        modifier = Modifier.size(312.dp, 1.dp)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                ItemReward("Americano", "24 June | 12:30 PM")
                Spacer(modifier = Modifier.height(20.dp))
                Box(
                    modifier = Modifier.fillMaxWidth()
                )
                {
                    Image(
                        painter = painterResource(R.drawable.icon_line),
                        contentDescription = "Icon Line",
                        modifier = Modifier.size(312.dp, 1.dp)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                ItemReward("Americano", "24 June | 12:30 PM")
                Spacer(modifier = Modifier.height(20.dp))
                Box(
                    modifier = Modifier.fillMaxWidth()
                )
                {
                    Image(
                        painter = painterResource(R.drawable.icon_line),
                        contentDescription = "Icon Line",
                        modifier = Modifier.size(312.dp, 1.dp)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                ItemReward("Americano", "24 June | 12:30 PM")
                Spacer(modifier = Modifier.height(20.dp))
                Box(
                    modifier = Modifier.fillMaxWidth()
                )
                {
                    Image(
                        painter = painterResource(R.drawable.icon_line),
                        contentDescription = "Icon Line",
                        modifier = Modifier.size(312.dp, 1.dp)
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))

            }
        }
    }
}





@Composable
fun PointCard(points : Int) {
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
                    .background(Color(red = 0.635f, green = 0.804f, blue = 0.914f, alpha = 0.19f)),
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

/*
@Preview(widthDp = 315, heightDp = 108)
@Composable
fun PointCardPreview()
{
    PointCard(2750)
}
*/

@Composable
fun ItemReward(A : String, B : String) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Column(
            modifier = Modifier.size(180.dp, 38.dp),
            verticalArrangement = Arrangement.SpaceBetween
        )
        {
            Text("$A", Modifier.wrapContentHeight(Alignment.Top), style = LocalTextStyle.current.copy(color = Color(0.2f, 0.29f, 0.35f, 1.0f), textAlign = TextAlign.Left, fontSize = 12.0.sp))
            Text("$B", Modifier.wrapContentHeight(Alignment.Top), style = LocalTextStyle.current.copy(color = Color(0.2f, 0.29f, 0.35f, 1.0f), textAlign = TextAlign.Left, fontSize = 10.0.sp))
        }
        Row(
            modifier = Modifier
                .padding(top = 4.dp, bottom = 10.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Text("+ 12 Pts", Modifier.wrapContentHeight(Alignment.Top), style = LocalTextStyle.current.copy(color = Color(0.2f, 0.29f, 0.35f, 1.0f), textAlign = TextAlign.Left, fontSize = 16.0.sp))
        }
    }
}









