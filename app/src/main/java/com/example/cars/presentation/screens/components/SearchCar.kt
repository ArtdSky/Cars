package com.example.cars.presentation.screens.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.cars.presentation.models.CarView
import com.example.cars.presentation.routing.Screen
import com.example.cars.presentation.viewmodel.MainViewModel

/**
 * Компонент Main экрана.
 * Служит для отображения списка элементов и их поиска
 *
 * @param items содержат список автомобилей
 * @param modifier принимает модификаторы
 * @param navController объект NavHostController, управляющий навигацией.
 * @param vm экземпляр ViewModel
 */
@Composable
fun SearchCar(
    vm: MainViewModel,
    modifier: Modifier,
    items: List<CarView>,
    navController: NavHostController
) {

    val state by vm.viewState.collectAsState()


    var searchText by remember { mutableStateOf("") }

    var filteredItems by remember { mutableStateOf(items) }
    LaunchedEffect(state.cars) {
        filteredItems = state.cars!!
    }
    fun filterItems(query: String): List<CarView> {
        return items.filter { it.name!!.contains(query, ignoreCase = true) }
    }

    var showDialog by remember { mutableStateOf(false) }


    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Подтвердите действие") },
                text = { Text("Приобрести подписку на 1 мин за 0р?") },
                confirmButton = {
                    Button(
                        onClick = {
                            showDialog = false
                            vm.buyTestSubscription()
                        }
                    ) {
                        Text("Купить")
                    }
                },
                dismissButton = {
                    Button(
                        onClick = { showDialog = false }
                    ) {
                        Text("Нет, спасибо")
                    }
                }
            )
        }
        OutlinedTextField(
            value = searchText,
            onValueChange = { text ->
                searchText = text
                filteredItems = filterItems(text)
            },
            label = {
                Text(
                    "Search"
                )
            },
            placeholder = { Text("Type here...") },
            shape = RoundedCornerShape(25.dp),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                cursorColor = MaterialTheme.colors.primary,
            )
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Сортировать по : ",
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            )
            Button(
                onClick = { vm.sortCarsFromDb("name") },
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                Text("name")
            }
            Button(
                onClick = { vm.sortCarsFromDb("year") },
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                Text("year")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = { vm.sortCarsFromDb("created") },
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                Text("created")
            }
            Button(
                onClick = { vm.sortCarsFromDb("engine") },
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                Text("engine")
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(filteredItems.size) { index ->
                val item = filteredItems[index]
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    item.name?.let {
                        Text(
                            text = it,
                            modifier = Modifier
                                .padding(16.dp)
                                .clickable {
                                    if (state.subscriptionInfo?.isUnlimitedSubscription == true) {
                                        vm.setCurrentItem(item)
                                        navController.navigate(Screen.CarInfo.route)

                                    } else if (state.subscriptionInfo?.watchCarsCount != 0) {
                                        vm.setCurrentItem(item)
                                        vm.decSubscriptionWatchCount()
                                        navController.navigate(Screen.CarInfo.route)
                                    } else {
                                        showDialog = true
                                    }
                                },
                            style = MaterialTheme.typography.h5
                        )
                    }
                    IconButton(
                        onClick = {
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.FavoriteBorder,
                            contentDescription = "favorites"
                        )
                    }
                }
                Divider()
            }
        }
    }
}