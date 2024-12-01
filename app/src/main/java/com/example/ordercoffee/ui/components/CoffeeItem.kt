// CoffeeItem.kt
package com.example.ordercoffee.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.Alignment
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ordercoffee.data.CoffeeProduct
import com.example.ordercoffee.data.CoffeeDataSource.PersonObject
import androidx.navigation.NavController
import com.example.ordercoffee.data.CoffeeDataSource.sampleItemCart
import com.example.ordercoffee.data.ItemCart

@Composable
fun CoffeeItem(
    coffeeProduct: CoffeeProduct,
    navController: NavController
) {
    // Truy cập PersonObject
    val personState = PersonObject.current

    Row(
        modifier = Modifier
            .width(160.dp)
            .padding(8.dp)
            .clickable {
                // Cập nhật NowProduct
                personState.value = personState.value.copy(NowItemCart = sampleItemCart.copy(coffeeProduct = coffeeProduct))
                // Điều hướng tới DetailScreen
                navController.navigate("detail_screen")
            },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card(
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFFBF4DB)
            )

        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = coffeeProduct.imageResId),
                    contentDescription = coffeeProduct.name,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(10.dp))
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = coffeeProduct.name,
                    style = LocalTextStyle.current.copy(
                        color = Color.Black,
                        fontSize = 14.sp
                    )
                )
            }
        }
    }
}
