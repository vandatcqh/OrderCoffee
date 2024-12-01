package com.example.ordercoffee.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
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
import androidx.compose.foundation.lazy.LazyRow
import com.example.ordercoffee.data.CoffeeDataSource
import com.example.ordercoffee.ui.components.CoffeeItem
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import com.example.ordercoffee.data.CoffeeDataSource.PersonObject
import com.example.ordercoffee.data.Person
import com.example.ordercoffee.ui.components.BottomNavigation
import kotlin.math.roundToInt
import androidx.compose.runtime.*
import androidx.compose.ui.text.font.FontWeight

@Preview(widthDp = 325, heightDp = 812)
@Composable
fun ScaffoldWithHorizontalScroll() {
    Scaffold(
        content = { paddingValues ->
            HorizontalScrollList(modifier = Modifier.padding(paddingValues))
        }
    )
}

@Composable
fun HorizontalScrollList(modifier: Modifier = Modifier) {
    var offsetX by remember { mutableStateOf(0f) } // Quản lý vị trí dịch chuyển danh sách

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .pointerInput(Unit) {
                detectHorizontalDragGestures { change, dragAmount ->
                    change.consume()
                    offsetX += dragAmount
                }
            }
    ) {
        LazyRow(
            modifier = Modifier
                .offset { IntOffset(offsetX.roundToInt(), 0) }
                .fillMaxSize(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(10) { index ->
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .background(
                            when (index) {
                                0 -> Color.Blue
                                1 -> Color.Red
                                2 -> Color.Yellow
                                else -> Color.Green
                            }
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Item $index",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

