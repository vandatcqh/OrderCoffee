package com.example.ordercoffee.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ordercoffee.LoyaltyCard
import com.example.ordercoffee.R
import com.example.ordercoffee.data.CoffeeDataSource.PersonObject
import com.example.ordercoffee.data.CoffeeProduct
import com.example.ordercoffee.ui.components.BottomNavigation



@Composable
fun RedeemScreen(navController: NavController)
{
    Column(
        modifier = Modifier
            .padding(start = 25.dp, top = 57.dp, end = 25.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top,
    )
    {
        Row()
        {
            Image(
                painter = painterResource(id = R.drawable.icon_arrow_left),
                contentDescription = "Quay lại",
                modifier = Modifier
                    .size(30.dp)
                    .clickable { navController.popBackStack() }
            )
            Spacer(modifier = Modifier.padding(start = 100.dp))
            Text(
                "Redeem",
                modifier = Modifier.wrapContentHeight(Alignment.Top),
                style = LocalTextStyle.current.copy(
                    color = Color(0xFF78B3CE),
                    textAlign = TextAlign.Center,
                    fontSize = 25.sp
                ),
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(50.dp))
        ItemRedeem(navController, "Espresso", painterResource(R.drawable.coffee_img_01), 400, "15/12/2024")
        ItemRedeem(navController, "Americano", painterResource(R.drawable.coffee_img_02), 350, "16/12/2024")
        ItemRedeem(navController, "Latte", painterResource(R.drawable.coffee_img_03), 270, "19/12/2024")
        ItemRedeem(navController, "Macchiato", painterResource(R.drawable.coffee_img_04), 290, "10/12/2024")

    }
}



@Composable
fun ItemRedeem(navController: NavController, name: String, CFIMG: Painter, PtsToGet: Int, valid:String) {
    val personState = PersonObject.current
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .height(80.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    ) {
        // Image
        Image(
            painter = CFIMG,
            contentDescription = "Coffee Image",
            modifier = Modifier
                .size(80.dp, 60.dp) // Kích thước của mỗi hình ảnh
        )

        // Column chứa 2 dòng Text
        Column(
            modifier = Modifier
                .size(150.dp, 41.dp)
        ) {
            Text(
                text = "$name",
                fontSize = 14.sp
            )
            Text(
                text = "Valid Until $valid",
                fontSize = 10.sp
            )
        }
        Row(
            modifier = Modifier
                .padding(top = 15.dp)
                .clip(RoundedCornerShape(15.dp))
                .size(100.dp, 30.dp)
                .background(Color(0xFF78B3CE))
                .clickable {
                    val numPoint = personState.value.numPoint
                    if (PtsToGet > numPoint) {
                        // Hiển thị Toast nếu điều kiện đúng
                        Toast.makeText(
                            context,
                            "You don't have enough points",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {

                        personState.value = personState.value.copy(numPoint = numPoint - PtsToGet)
                        navController.navigate("reward_screen")
                    }
                },
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Text(
                text = "$PtsToGet pts",
                color = Color.White,
                fontSize = 12.sp
            )
        }
    }
}
