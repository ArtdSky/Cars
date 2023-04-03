package com.example.cars.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.example.cars.presentation.routing.NavState
import com.example.cars.presentation.ui.theme.CarsTheme
import com.example.cars.presentation.viewmodel.MainViewModel
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            CarsTheme {
                MainActivityScreen()
            }
        }
    }
}

@Composable
fun MainActivityScreen() {
    val myViewModel: MainViewModel = koinViewModel()
    myViewModel.getAllCarsOutDb()
    myViewModel.getAllSubscriptionsOutDb()
    NavState(
        vm = myViewModel
    )
}





