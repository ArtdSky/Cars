package com.example.cars.presentation.screens.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.cars.presentation.routing.Screen


/**
 * Экран для выдвижного меню навигации экранов.
 *
 * @param navController объект NavHostController, управляющий навигацией.
 * @param closeDrawerAction callback для изменения состояния [drawerState]
 * @param currentScreen определяет текущий экран для навигации
 */
@Composable
fun MainScreenDrawer(
    navController: NavHostController,
    closeDrawerAction: () -> Unit,
    currentScreen: Screen
) {
    Column(modifier = Modifier.fillMaxSize()) {

        Surface(
            modifier = Modifier.weight(1f)
        ) {
            ScreenNavigationButton(
                icon = Icons.Filled.Home,
                isSelected = currentScreen == Screen.Main,
                onClick = {
                    navController.navigate(Screen.Main.route)
                    closeDrawerAction()
                }
            )
        }
        Surface(
            modifier = Modifier.weight(1f)
        ) {
            ScreenNavigationButton(
                icon = Icons.Filled.Add,
                isSelected = currentScreen == Screen.Add,
                onClick = {
                    navController.navigate(Screen.Add.route)
                    closeDrawerAction()
                }
            )
        }

        Surface(
            modifier = Modifier.weight(1f)
        ) {
            ScreenNavigationButton(
                icon = Icons.Filled.Settings,
                isSelected = currentScreen == Screen.Settings,
                onClick = {
                    navController.navigate(Screen.Settings.route)
                    closeDrawerAction()
                }
            )
        }
    }
}

