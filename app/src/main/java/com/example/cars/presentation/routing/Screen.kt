package com.example.cars.presentation.routing

/**
 * класс прдставления экранов приложения,
 * подклассы: Main, Details, Settings и Add,
 * каждый из которых представляет собой экран приложения
 * и содержит свой уникальный [route]
 */

sealed class Screen(val route: String) {
    object Main : Screen("main_screen")
    object CarInfo : Screen("details_screen")
    object Settings : Screen("settings_screen")
    object Add : Screen("add_screen")

}

/**
 * Список содержащий все маршруты экранов приложения
 */
val AppTabRowScreens = listOf(
    Screen.Main,
    Screen.CarInfo,
    Screen.Settings,
    Screen.Add
)