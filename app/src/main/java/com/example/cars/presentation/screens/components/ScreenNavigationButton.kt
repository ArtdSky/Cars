package com.example.cars.presentation.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

/**
 * Экран кнопки для выдвижного меню навигации экранов.
 *
 * @param icon — иконка для отображения
 * @param onClick callback для изменения состояния [drawerState]
 * @param isSelected используется для определения того, выбран ли текущий экран
 */
@Composable
fun ScreenNavigationButton(
    icon: ImageVector,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val colors = MaterialTheme.colors
    val textColor = if (isSelected) colors.primary else colors.onSurface.copy(alpha = 0.6f)
    val backgroundColor = if (isSelected) colors.primary.copy(alpha = 0.12f) else colors.surface
    val imageAlpha = if (isSelected) 1f else 0.6f
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, top = 8.dp),
        color = backgroundColor,
        shape = MaterialTheme.shapes.small
    ) {
        Image(
            imageVector = icon,
            contentDescription = "Screen Navigation Button",
            colorFilter = ColorFilter.tint(textColor),
            alpha = imageAlpha,
            modifier = Modifier
                .clickable(onClick = onClick)
                .fillMaxWidth()
                .padding(4.dp)
        )
    }
}