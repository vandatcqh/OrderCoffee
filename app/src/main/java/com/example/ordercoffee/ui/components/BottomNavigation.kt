package com.example.ordercoffee.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ordercoffee.R

@Composable
fun BottomNavigation(navController: NavController, state: String) {
    val homeIconRes = if (state == "home") R.drawable.icon_home_1 else R.drawable.icon_home_0
    val rewardIconRes = if (state == "reward") R.drawable.icon_reward_1 else R.drawable.icon_reward_0
    val myOrderIconRes = if (state == "myorder") R.drawable.icon_myorder_1 else R.drawable.icon_myorder_0

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(Color(0xFFFBF4DB))
    ) {
        Row(
            modifier = Modifier
                .padding(
                    start = 56.dp,
                    top = 21.dp,
                    end = 60.87.dp,
                    bottom = 21.dp
                )
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            // Home Button
            Image(
                painter = painterResource(homeIconRes),
                contentDescription = "Home Button",
                modifier = Modifier
                    .size(23.dp)
                    .let {
                        if (state != "home") {
                            it.clickable {
                                navController.navigate("home_screen")
                            }
                        } else {
                            it
                        }
                    }
            )

            // Reward Button
            Image(
                painter = painterResource(rewardIconRes),
                contentDescription = "Reward Button",
                modifier = Modifier
                    .size(23.dp)
                    .let {
                        if (state != "reward") {
                            it.clickable {
                                navController.navigate("reward_screen")
                            }
                        } else {
                            it
                        }
                    }
            )

            // MyOrder Button
            Image(
                painter = painterResource(myOrderIconRes),
                contentDescription = "MyOrder Button",
                modifier = Modifier
                    .size(23.dp)
                    .let {
                        if (state != "myorder") {
                            it.clickable {
                                navController.navigate("myorder_screen")
                            }
                        } else {
                            it
                        }
                    }
            )
        }
    }
}
