package com.example.ordercoffee

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ordercoffee.data.CoffeeDataSource
import com.example.ordercoffee.ui.screens.DetailScreen
import com.example.ordercoffee.ui.screens.HomeScreen
import com.example.ordercoffee.ui.theme.OrderCoffeeTheme
import kotlinx.coroutines.delay
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.ordercoffee.data.CoffeeDataSource.PersonObject
import com.example.ordercoffee.data.CoffeeDataSource.samplePerson
import com.example.ordercoffee.data.CoffeeProduct
import com.example.ordercoffee.data.Order
import com.example.ordercoffee.data.Person
import com.example.ordercoffee.ui.screens.MyCartScreen
import com.example.ordercoffee.ui.screens.MyOrderScreen
import com.example.ordercoffee.ui.screens.OrderSuccess
import java.time.LocalDateTime
import com.example.ordercoffee.ui.screens.ProfileScreen
import com.example.ordercoffee.ui.screens.RedeemScreen
import com.example.ordercoffee.ui.screens.RewardScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OrderCoffeeTheme {
                val navController = rememberNavController()
                val appData =
                    remember { mutableStateOf(samplePerson) }
                CompositionLocalProvider(PersonObject provides appData)
                {
                    SetupNavGraph(navController = navController)
                }
            }
        }
    }
}

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "splash_screen"
    ) {
        composable("splash_screen") {
            SplashScreen(navController)
        }
        composable("home_screen") {
            HomeScreen(navController)
        }
        composable("detail_screen") {
            DetailScreen(navController)
        }
        composable("mycart_screen") {
            MyCartScreen(navController)
        }
        composable("ordersuccess_screen") {
            OrderSuccess(navController)
        }
        composable("reward_screen") {
            RewardScreen(navController)
        }
        composable("redeem_screen")
        {
            RedeemScreen(navController)
        }
        composable("myorder_screen") {
            MyOrderScreen(navController)
        }


        // Add the new route for ProfileScreen
        composable("profile_screen") {
            ProfileScreen(navController = navController)
        }
    }
}


@Composable
fun SplashScreen(navController: NavHostController) {
    Image(
        painter = painterResource(id = R.drawable.splash_img),
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
    LaunchedEffect(key1 = true) {
        delay(2000L)
        navController.navigate("home_screen") {
            popUpTo("splash_screen") { inclusive = true }
        }
    }
}
