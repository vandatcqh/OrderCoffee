package com.example.ordercoffee

import androidx.compose.foundation.Image
import androidx.compose.foundation. Image
import androidx.compose.foundation.background
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


@Composable
fun LoyaltyCard(n: Int = 4) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFF78B3CE))
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
                text = "$n / 8",
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
                            id = if (id < n) R.drawable.coffee_cup_1 else R.drawable.coffee_cup_0
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

@Preview(widthDp = 725, heightDp = 122)
@Composable
fun LoyaltyCardPreview() {
    LoyaltyCard(7)
}




