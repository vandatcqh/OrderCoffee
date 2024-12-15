package com.example.ordercoffee.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import com.example.ordercoffee.data.CoffeeDataSource.PersonObject


@Composable
fun ProfileScreen(navController: NavController) {
    val personState = PersonObject.current
    val person = personState.value
    Column(
        modifier = Modifier
            .padding(33.dp)
            .padding(top = 25.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Profile", Modifier.wrapContentHeight(Alignment.Top), style = LocalTextStyle.current.copy(color = Color(0.0f, 0.09f, 0.2f, 1.0f), textAlign = TextAlign.Center, fontSize = 22.0.sp))

        ProfileField(
            iconResId = R.drawable.icon_name_edit,
            label = "Full Name",
            value = person.name,
            onValueChange = { personState.value = person.copy(name = it) }
        )

        ProfileField(
            iconResId = R.drawable.icon_phone_edit,
            label = "Phone Number",
            value = person.phone,
            onValueChange = { personState.value = person.copy(phone = it) }
        )

        ProfileField(
            iconResId = R.drawable.icon_email_edit,
            label = "Email",
            value = person.email,
            onValueChange = { personState.value = person.copy(email = it) }
        )

        ProfileField(
            iconResId = R.drawable.icon_address_edit,
            label = "Address",
            value = person.address,
            onValueChange = { personState.value = person.copy(address = it) }
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clip(RoundedCornerShape(30.dp))
                .background(Color(0xFF80C4E9))
                .clickable { navController.popBackStack() }
                .padding(vertical = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Back to Homescreen",
                color = Color.White
                //style = MaterialTheme.typography.button
            )
        }
    }

}

@Composable
fun ProfileField(
    iconResId: Int,
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    var isEditing by remember { mutableStateOf(false) }
    var textValue by remember { mutableStateOf(value) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { isEditing = true }, // Mở hộp thoại khi nhấn
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(iconResId),
                contentDescription = null,
                modifier = Modifier.size(42.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = label,
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = Color(0xFF001733),
                        fontSize = 16.sp
                    )
                )
                Text(
                    text = value,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = Color(0xFF334A59),
                        fontSize = 15.sp
                    )
                )
            }
        }
        Image(
            painter = painterResource(R.drawable.icon_edit_profile),
            contentDescription = "Edit",
            modifier = Modifier
                .padding(9.dp)
                .size(24.dp)
                .clickable { isEditing = true } // Mở hộp thoại khi nhấn
        )
    }

    if (isEditing) {
        AlertDialog(
            onDismissRequest = { isEditing = false },
            title = { Text(text = "Edit $label") },
            text = {
                TextField(
                    value = textValue,
                    onValueChange = { newText ->
                        textValue = newText
                    },
                    label = { Text("Enter new value") },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = MaterialTheme.typography.bodyLarge.copy(
                        color = Color(0xFF334A59),
                        fontSize = 15.sp
                    ),
                    singleLine = true,
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color(0xFF80C4E9),
                        unfocusedIndicatorColor = Color(0xFF80C4E9),
                    )
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onValueChange(textValue)
                        isEditing = false
                    },
                ) {
                    Text("Save")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        isEditing = false
                    },
                ) {
                    Text("Cancel")
                }
            },
            containerColor = Color.White,
        )
    }
}
