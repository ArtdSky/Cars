package com.example.cars.presentation.screens

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.cars.presentation.routing.Screen
import com.example.cars.presentation.screens.components.MainScreenDrawer
import com.example.cars.presentation.screens.components.SearchCar
import com.example.cars.presentation.viewmodel.MainViewModel
import kotlinx.coroutines.launch
import java.util.*

/**
 * Главный экран приложения.
 *
 * @param navController объект NavHostController, управляющий навигацией.
 * @param vm экземпляр ViewModel
 * @param currentScreen определяет текущий экран для навигации
 */
@Composable
fun MainScreen(
    vm: MainViewModel,
    navController: NavHostController,
    currentScreen: Screen
) {

    val state by vm.viewState.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    Log.d("MainScreen", state.toString())

    val scaffoldState: ScaffoldState = rememberScaffoldState()

    state.subscriptionInfo?.let { subscriptionInfo ->
        if (subscriptionInfo.end != null && subscriptionInfo.end < Date().time) {
            vm.deleteSubscription()
        }
    }

    val items = remember(state.cars) { mutableStateOf(state.cars) }

    Scaffold(
        scaffoldState = scaffoldState,
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
        modifier = Modifier.fillMaxSize(),
    ) { paddingValues ->
        items.value?.let {
            SearchCar(
                vm = vm,
                items = it,
                navController = navController,
                modifier = Modifier.padding(paddingValues),
            )
        }
    }
}

