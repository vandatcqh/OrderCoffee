package com.example.ordercoffee

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation. Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ordercoffee.LoyaltyCard
import com.example.ordercoffee.R
import com.example.ordercoffee.data.CoffeeDataSource.PersonObject


@Composable
fun LoyaltyCard(n: Int) {
    val context = LocalContext.current
    val personState = PersonObject.current
    val cups = n % 8
    val colorIndex = (n / 8) % 5
    val backgroundColors = listOf(
        Color(0xFF78B3CE),
        Color(0xFFA66E38), // Màu 1
        Color(0xFFA6AEBF), // Màu 2
        Color(0xFFF3C623), // Màu 3
        Color(0xFF9ABF80), // Màu 4
        Color(0xFF8174A0)  // Màu 5
    )
    val backgroundColor = backgroundColors.getOrElse(colorIndex) { Color(0xFF740938) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(backgroundColor)
            .clickable
            {
                val numLoyal = personState.value.numLoyalty
                if (personState.value.numLoyalty < 8) {
                    // Hiển thị Toast nếu điều kiện đúng
                    Toast.makeText(
                        context,
                        "Your loyalty card does not meet the requirements.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {

                    personState.value = personState.value.copy(numLoyalty = personState.value.numLoyalty - 8)
                    //navController.navigate("reward_screen")
                }

            }
            //.background(Color(0.2f, 0.29f, 0.35f, 1.0f))
    )
    {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 30.dp,
                    top = 14.dp,
                    end = 34.dp,
                    bottom = 87.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Text(
                "Loyalty card",
                style = LocalTextStyle.current.copy(
                    color = Color(0xFFFBF4DB),
                    fontSize = 14.sp,
                ),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "$cups / 8",
                style = LocalTextStyle.current.copy(
                    color = Color(0xFFFBF4DB),
                    fontSize = 14.sp
                ),
                fontWeight = FontWeight.Bold
            )
        }
        Row(
            modifier = Modifier
                .padding(
                    start = 23.dp,
                    top = 44.dp,
                    end = 23.dp,
                    bottom = 17.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(Color.White),
        ) {
            Row(
                modifier = Modifier
                    .padding(
                        start = 11.dp,
                        top = 15.dp,
                        end = 12.44.dp,
                        bottom = 17.dp
                    )
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            )
            {
                repeat(8) { id ->
                    Image(
                        painter = painterResource(
                            id = if (id < cups) R.drawable.coffee_cup_1 else R.drawable.coffee_cup_0
                        ),
                        contentDescription = "Coffee Cup",
                        modifier = Modifier
                            .size(30.dp) // Kích thước của mỗi hình ảnh
                    )
                }
            }
        }
    }
}





