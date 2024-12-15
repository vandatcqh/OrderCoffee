package com.example.ordercoffee.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ordercoffee.LoyaltyCard
import com.example.ordercoffee.R
import androidx.navigation.NavController
import androidx.compose.foundation.lazy.LazyColumn
import com.example.ordercoffee.data.CoffeeDataSource
import com.example.ordercoffee.ui.components.CoffeeItem
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import com.example.ordercoffee.data.CoffeeDataSource.PersonObject
import com.example.ordercoffee.data.Person
import com.example.ordercoffee.ui.components.BottomNavigation


@Composable
fun HomeScreen(navController: NavController) {
    val personState = PersonObject.current
    Scaffold(
        containerColor = Color.White,
        bottomBar = {
            BottomAppBar(
                containerColor =Color(0xFF78B3CE),
                contentPadding = PaddingValues(horizontal = 25.dp, vertical = 0.dp)
            )
            {
                BottomNavigation(navController, "home")
            }
        }
    )
    {
        paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        ) {
            Spacer(modifier = Modifier.height(30.dp))
            Box(
                modifier = Modifier.padding(horizontal = 25.dp)
            )
            {
                GreetingAndProfile(navController)
            }
            Spacer(modifier = Modifier.height(18.dp))
            Box(
                modifier = Modifier.padding(horizontal = 25.dp)
            )
            {
                LoyaltyCard(personState.value.numLoyalty)
            }
            Spacer(modifier = Modifier.height(38.dp))
            MenuCoffee(navController)
        }
    }
}

@Composable
fun CoffeeList(navController: NavController) {
    val coffeeList = CoffeeDataSource.coffeeList
    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 16.dp)
    ) {
        val rows = coffeeList.chunked(2)
        items(rows) { rowItems ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                for (coffeeProduct in rowItems) {
                    CoffeeItem(coffeeProduct, navController)
                }
                if (rowItems.size < 2) {
                    Spacer(modifier = Modifier.width(160.dp))
                }
            }
        }
    }
}


@Composable
fun GreetingAndProfile(navController: NavController)
{
    val appData = PersonObject.current
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Good morning",
                style = LocalTextStyle.current.copy(
                    color = Color(0xFF78B3CE),
                    textAlign = TextAlign.Left,
                    fontSize = 14.sp
                )
            )
            Text(
                text = appData.value.name,
                style = LocalTextStyle.current.copy(
                    color = Color(0.0f, 0.09f, 0.2f, 1.0f),
                    fontSize = 18.sp
                ),
                fontWeight = FontWeight.Bold
            )
        }
        Row(
            modifier = Modifier
                .padding(top = 7.dp, end = 8.dp),
            horizontalArrangement = Arrangement.End,
        ) {
            Image(
                painter = painterResource(id = R.drawable.iconly_light_buy),
                contentDescription = "Buy Icon",
                modifier = Modifier
                    .size(26.dp)
                    .clickable {
                        navController.navigate("mycart_screen")
                    }
            )

            Spacer(modifier = Modifier.width(20.dp))
            Image(
                painter = painterResource(id = R.drawable.iconly_light_profile),
                contentDescription = "Profile Icon",
                modifier = Modifier
                    .size(26.dp)
                    .clickable {
                        navController.navigate("profile_screen")
                    }
            )
        }
    }
}

@Composable
fun MenuCoffee(navController : NavController)
{
    Box(
        Modifier
            .clip(RoundedCornerShape(25.0.dp, 25.0.dp, 0.0.dp, 0.0.dp))
            .background(Color(0xFF78B3CE))
            .fillMaxSize(),
    ) {
        Column(
            modifier = Modifier.padding(start = 25.dp, top = 24.dp, end = 25.dp, bottom = 21.34.dp).fillMaxSize(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween
        )
        {
            Text(
                text = "Choose your coffee",
                modifier = Modifier
                    .fillMaxWidth(),
                style = LocalTextStyle.current.copy(
                    color = Color(0xFFFBF4DB),
                    fontSize = 16.sp,
                ),
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(15.dp))
            CoffeeList(navController)
            BottomNavigation(navController, "home")
        }
    }
}


