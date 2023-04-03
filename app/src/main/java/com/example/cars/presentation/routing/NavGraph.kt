package com.example.cars.presentation.routing

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cars.presentation.screens.*
import com.example.cars.presentation.viewmodel.MainViewModel

/**
 * Определяет граф навигации в приложении с помощью `NavHost`.
 * Граф навигации состоит из четырех экранов: основной экран [Main],
 * экран добавления автомобиля [AddCar], экран информации об автомобиле [CarInfo],
 * и экран настроек [Настройки].
 * Экраны связаны соответствующими маршрутами, определенными в `Main`, `AddCar`, `CarInfo` и `Settings`
 * объекты в закрытом классе [Screen].
 *
 * @param navController — объект NavHostController, управляющий навигацией.
 * @param vm экземпляр ViewModel
 * @param currentScreen определяет текущий экран навигации, используя список AppTabRowScreens
 */
@Composable
fun NavGraph(
    vm: MainViewModel,
    navController: NavHostController,
    currentScreen: Screen
) {

    NavHost(
        navController = navController,
        startDestination = Screen.Main.route
    ) {
        composable(route = Screen.Main.route) {
            MainScreen(
                vm = vm,
                navController = navController,
                currentScreen = currentScreen
            )
        }

        composable(route = Screen.Add.route) {
            AddCar(
                vm = vm,
                navController = navController
            )
        }

        composable(route = Screen.Settings.route) {
            Settings(
                vm = vm,
                navController = navController,
                currentScreen = currentScreen
            )
        }

        composable(
            route = Screen.CarInfo.route
        ) {
            CarInfo(
                vm = vm,
                navController = navController
            )
        }
    }

}