package com.example.cars.presentation.routing

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.cars.presentation.viewmodel.MainViewModel

/**
 * Функция NavState определяет текущее состояние навигации
 * Создает объект `navController`, который управляет навигацией с помощью функции `rememberNavController`.
 * Затем создает навигационный граф, используя `NavGraph`
 *
 * @param vm экземпляр ViewModel
 */
@Composable
fun NavState(
    vm : MainViewModel
) {

    val navController = rememberNavController()
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStack?.destination
    val currentScreen = AppTabRowScreens.find {
        it.route == currentDestination?.route
    } ?: Screen.Main

    NavGraph(
        vm = vm,
        navController = navController,
        currentScreen = currentScreen,
    )

}