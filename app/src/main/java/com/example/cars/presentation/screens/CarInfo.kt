package com.example.cars.presentation.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.cars.presentation.viewmodel.MainViewModel
import java.util.*

/**
 * Экран информации об автомобиле.
 *
 * @param vm экземпляр ViewModel
 * @param navController объект NavHostController, управляющий навигацией.
 */

@SuppressLint("DiscouragedApi")
@Composable
fun CarInfo(
    vm: MainViewModel,
    navController: NavHostController,
) {

    val state by vm.viewState.collectAsState()
    val item = state.currentCar
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (item != null) {

            item.name?.let { Text(text = it, style = MaterialTheme.typography.body1) }
            item.imageString?.let { image ->
                val resId = context.resources.getIdentifier(image, "drawable", context.packageName)
                Image(
                    painter = painterResource(id = resId),
                    contentDescription = null,
                    modifier = Modifier.size(200.dp),
                    contentScale = ContentScale.Fit
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            item.year?.let { Text(text = "Year: $it", style = MaterialTheme.typography.body1) }
            item.engineVolume?.let {
                Text(
                    text = "Engine Volume: $it",
                    style = MaterialTheme.typography.body1
                )
            }
            item.createdAt?.let {
                Text(
                    text = "Created at: $it",
                    style = MaterialTheme.typography.body1
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { navController.popBackStack() }
            ) {
                Text(text = "Close")
            }
        }
    }
}