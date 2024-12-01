
package com.example.ordercoffee.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ordercoffee.R
import com.example.ordercoffee.data.CoffeeProduct
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.ordercoffee.data.CoffeeDataSource.PersonObject
import com.example.ordercoffee.data.ItemCart

@Composable
fun DetailScreen(navController: NavController) {
    val personState = PersonObject.current
    val ItemCart = personState.value.NowItemCart

    // Check if coffeeProduct is not null
    if (ItemCart == null) {
        // Handle null case, maybe navigate back or show an error
        Text("No product selected.")
        return
    }
    var quantity by remember { mutableStateOf(ItemCart.quantity) }
    var selectedShot by remember { mutableStateOf(ItemCart.shot) }
    var selectedCup by remember { mutableStateOf(ItemCart.select) }
    var selectedSize by remember { mutableStateOf(ItemCart.size) }
    var selectedIce by remember { mutableStateOf(ItemCart.iceLevel) }
    // Calculate total amount
    val basePrice by remember {
        derivedStateOf {
            when (selectedSize) {
                "M" -> ItemCart.coffeeProduct.price + 5
                "L" -> ItemCart.coffeeProduct.price + 10
                else -> ItemCart.coffeeProduct.price
            }
        }
    }
    val totalAmount = basePrice * quantity
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(25.dp),

    ) {
        // Top App Bar
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.icon_arrow_left),
                contentDescription = "Back",
                modifier = Modifier
                    .size(30.dp)
                    .clickable { navController.popBackStack() }
            )
            Text(
                text = "Details",
                style = LocalTextStyle.current.copy(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
            IconButton(onClick = {

                navController.navigate("mycart_screen")
            }) {
                Icon(Icons.Default.ShoppingCart, contentDescription = "Cart")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFFD4EBF8)),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Image(
                painter = painterResource(id = ItemCart.coffeeProduct.imageResId),
                contentDescription = "Coffee Image",
                modifier = Modifier.size(172.dp, 128.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Coffee Name and Quantity
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = ItemCart.coffeeProduct.name,
                style = LocalTextStyle.current.copy(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Row(
                modifier = Modifier
                    .border(
                        width = 1.2.dp,
                        color = Color(red = 0.847f, green = 0.847f, blue = 0.847f, alpha = 0.6f),
                        shape = RoundedCornerShape(50.dp)
                    )
                    .clip(RoundedCornerShape(50.dp)),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically

            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(
                        onClick = {
                            if (quantity > 1) quantity--
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_decrease),
                            contentDescription = "Decrease",
                            modifier = Modifier.size(32.dp) // Thay đổi kích thước biểu tượng tại đây
                        )
                    }
                    Text(
                        text = "$quantity",
                        style = LocalTextStyle.current.copy(fontSize = 18.sp)
                    )
                    IconButton(
                        onClick = {
                            quantity++
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_increase),
                            contentDescription = "Increase",
                            modifier = Modifier.size(32.dp) // Thay đổi kích thước biểu tượng tại đây
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Options: Shot, Select, Size, Ice
        OptionRow(
            label = "Shot",
            options = listOf("Single", "Double"),
            selectedOption = selectedShot,
            onOptionSelected = { selectedShot = it }
        )
        OptionRow(
            label = "Select",
            options = listOf("Drink In", "Take Away"),
            selectedOption = selectedCup,
            onOptionSelected = { selectedCup = it }
        )
        OptionRow(
            label = "Size",
            options = listOf("S", "M", "L"),
            selectedOption = selectedSize,
            onOptionSelected = { selectedSize = it }
        )
        OptionRow(
            label = "Ice",
            options = listOf("No", "Less", "Normal"),
            selectedOption = selectedIce,
            onOptionSelected = { selectedIce = it }
        )

        Spacer(modifier = Modifier.height(150.dp))

        // Total Amount and Add to Cart Button
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Total Amount",
                style = LocalTextStyle.current.copy(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
            )
            Text(
                text = "$$totalAmount",
                style = LocalTextStyle.current.copy(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                val itemCart = ItemCart(
                    coffeeProduct = ItemCart.coffeeProduct,
                    quantity = quantity,
                    TotalPrice = totalAmount,
                    TotalPoint = quantity*12,
                    shot = selectedShot,
                    select = selectedCup,
                    size = selectedSize,
                    iceLevel = selectedIce
                )
                val updatedListCart = personState.value.ListCart + itemCart
                personState.value = personState.value.copy(ListCart = updatedListCart)
                personState.value = personState.value.copy(NowItemCart = itemCart)
                // Navigate to MyCartScreen
                navController.navigate("mycart_screen")
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
            Text(text = "Add to cart")
        }
    }
}

@Composable
fun OptionRow(
    label: String,
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Label aligned to the left
        Text(
            text = label,
            style = LocalTextStyle.current.copy(
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        )

        // Buttons aligned to the right
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            options.forEach { option ->
                OutlinedButton(
                    onClick = { onOptionSelected(option) },
                    border = if (option == selectedOption) ButtonDefaults.outlinedButtonBorder else null,
                    colors = if (option == selectedOption) ButtonDefaults.outlinedButtonColors(
                        contentColor = MaterialTheme.colorScheme.primary,
                        containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.12f)
                    ) else ButtonDefaults.outlinedButtonColors()
                ) {
                    Text(text = option)
                }
            }
        }
    }
}

