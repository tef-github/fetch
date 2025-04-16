package com.example.fetch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.fetch.ui.theme.FetchTheme
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.fetch.ui.screen.DetailScreen
import com.example.fetch.ui.screen.ItemsListScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FetchTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "list") {
                    composable("list") {
                        ItemsListScreen(navController = navController)
                    }
                    composable(
                        "detail/{itemName}",
                        arguments = listOf(navArgument("itemName") { type = NavType.StringType })
                    ) { backStackEntry ->
                        val itemName = backStackEntry.arguments?.getString("itemName") ?: ""
                        DetailScreen(itemName)
                    }
                }
            }
        }
    }
}


