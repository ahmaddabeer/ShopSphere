package com.example.shopsphere

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavController
import com.example.shopsphere.presentation.navigation.NavController
import com.example.shopsphere.presentation.screen.home.HomeScreen
import com.example.shopsphere.presentation.ui.theme.ShopSphereTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShopSphereTheme {

                NavController()






            }
        }
    }
}

