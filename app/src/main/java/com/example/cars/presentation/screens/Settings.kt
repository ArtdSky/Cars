package com.example.cars.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.cars.presentation.routing.Screen
import com.example.cars.presentation.screens.components.MainScreenDrawer
import com.example.cars.presentation.viewmodel.MainViewModel
import kotlinx.coroutines.launch

/**
 * Экран для сброса настроек приложения
 * @param navController объект NavHostController, управляющий навигацией.
 * @param vm экземпляр ViewModel
 * @param currentScreen определяет текущий экран для навигации
 */

@Composable
fun Settings(
    vm: MainViewModel,
    navController: NavHostController,
    currentScreen: Screen
) {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    val state by vm.viewState.collectAsState()
    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxSize(),
        drawerContent = {
            MainScreenDrawer(
                currentScreen = currentScreen,
                navController = navController,
                closeDrawerAction = {
                    coroutineScope.launch {
                        scaffoldState.drawerState.close()
                    }
                }
            )
        },
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                Button(
                    onClick = {
                        coroutineScope.launch {
                            vm.clearDb().await()
                            vm.loadDefaultData().await()
                            vm.sendSnackbar(scaffoldState, "database cleared")

                        }
                    },
                ) {
                    Text("Reset App")
                }


            }

        }
    }
}