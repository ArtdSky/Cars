package com.example.cars.presentation.screens

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.cars.presentation.models.CarView
import com.example.cars.presentation.viewmodel.MainViewModel

/**
 * Экран для добавления данных автомобиля в бд.
 *
 * @param vm экземпляр ViewModel
 * @param navController объект NavHostController, управляющий навигацией.
 */
@Composable
fun AddCar(
    vm: MainViewModel,
    navController: NavHostController
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        val onAddCar: (CarView) -> Unit = { car -> Log.d("TAG", car.toString()) }

        var name by remember { mutableStateOf("") }
        var year by remember { mutableStateOf("") }
        var engineVolume by remember { mutableStateOf("") }
        var imageUri by remember { mutableStateOf<Uri?>(null) }

        // обработка результата выбора изображений.
        val launcher =
            rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    result.data?.data?.let {
                        imageUri = it
                    }
                }
            }

        // Функция для запуска выбора изображений
        fun pickImage() {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type = "image/*"
            intent.putExtra(Intent.EXTRA_MIME_TYPES, arrayOf("image/jpeg", "image/png"))
            launcher.launch(intent)
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Add New Car",
                style = MaterialTheme.typography.h5,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            if (imageUri != null) {
                Image(
                    painter = rememberAsyncImagePainter(model = imageUri),
                    contentDescription = null,
                    modifier = Modifier
                        .size(128.dp)
                        .padding(16.dp)
                )
            }

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = year,
                onValueChange = { year = it },
                label = { Text("Year") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = engineVolume,
                onValueChange = { engineVolume = it },
                label = { Text("Engine Volume") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val car = CarView(
                        name = name,
                        imageString = imageUri.toString(),
                        year = year.toIntOrNull(),
                        engineVolume = engineVolume.toDoubleOrNull()
                    )
                    onAddCar(car)
                    Log.d("TAG", imageUri.toString())
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(text = "Add Car")
            }

            Button(
                onClick = { pickImage() },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(text = "Pick Image")
            }
        }
    }

}