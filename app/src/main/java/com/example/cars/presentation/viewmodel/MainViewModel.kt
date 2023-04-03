package com.example.cars.presentation.viewmodel

import android.content.Context
import android.util.Log
import androidx.compose.material.ScaffoldState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cars.domain.usecases.ResetAppUseCase
import com.example.cars.domain.usecases.cars.AddNewCarToDbUseCase
import com.example.cars.domain.usecases.cars.GetAllCarsInDbUseCase
import com.example.cars.domain.usecases.cars.GetCarByIdInDbUseCase
import com.example.cars.domain.usecases.cars.SortCarsFromDbUseCase
import com.example.cars.domain.usecases.subscription.AddNewSubscriptionUseCase
import com.example.cars.domain.usecases.subscription.CheckSubInDbUseCase
import com.example.cars.domain.usecases.subscription.GetAllSubscriptionInDbUseCase
import com.example.cars.presentation.models.CarView
import com.example.cars.presentation.models.SubscriptionView
import com.example.cars.presentation.state.ViewState
import com.example.cars.presentation.utils.GetStaticCarsData
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.*

/**
 * ViewModel for MainActivity
 * @param usecases from domain layer
 */
class MainViewModel(
    private val context: Context,
    private val addNewCarToDbUseCase: AddNewCarToDbUseCase,
    private val getAllCarsInDbUseCase: GetAllCarsInDbUseCase,
    private val getCarByIdInDbUseCase: GetCarByIdInDbUseCase,
    private val sortCarsFromDbUseCase: SortCarsFromDbUseCase,
    private val addNewSubToDbUseCase: AddNewSubscriptionUseCase,
    private val checkSubInDbUseCase: CheckSubInDbUseCase,
    private val resetAppUseCase: ResetAppUseCase,
    private val getAllSubscriptionInDbUseCase: GetAllSubscriptionInDbUseCase
) : ViewModel() {

    private val _viewState = MutableStateFlow(
        ViewState()
    )
    val viewState = _viewState.asStateFlow()

    init {
        viewModelScope.launch {
            checkInitialDbData().await()
            loadDefaultData().await()

            getAllCarsOutDb()
            getAllSubscriptionsOutDb()
        }
    }

    fun sortCarsFromDb(sortBy : String){
        viewModelScope.launch {
           val cars = sortCarsFromDbUseCase(sortBy)
            _viewState.update { currentState ->
                currentState.copy(
                    cars = cars
                )
            }
        }
    }

    private fun checkInitialDbData(): Deferred<Unit> {
        return viewModelScope.async(Dispatchers.IO) {
            val cars = getAllCarsInDbUseCase()
            val subs = getAllSubscriptionInDbUseCase()
            if (cars.isEmpty() && subs.isEmpty()) {
                _viewState.update { currentState ->
                    currentState.copy(
                        isNeedData = true
                    )
                }
            }
        }
    }

    fun loadDefaultData(): Deferred<Unit> {
        return viewModelScope.async(Dispatchers.IO) {
            if (_viewState.value.isNeedData == true) {
                createInitialSubscription().await()
                createInitialCarsData().await()
                _viewState.update { currentState ->
                    currentState.copy(
                        isNeedData = false,
                    )
                }
            }
        }
    }

    private fun createInitialSubscription() : Deferred<Unit> {
        return viewModelScope.async(Dispatchers.IO) {
            val subscribe = SubscriptionView(
                id = 1,
                start = Date().time,
                watchCarsCount = 3,
                addToFavoriteCount = 2
            )
            _viewState.update { currentState ->
                currentState.copy(
                    subscriptionInfo = subscribe,
                )
            }

            updateSubscriptionInDb()
        }
    }

    fun updateSubscriptionInDb() {
        viewModelScope.launch {
            val subscription = _viewState.value.subscriptionInfo
            subscription?.let {
                addNewSubToDbUseCase(it)
            }
        }
    }

    private fun createInitialCarsData() : Deferred<Unit> {
        return viewModelScope.async(Dispatchers.IO) {
            val cars = GetStaticCarsData.getData(context)
            _viewState.update { currentState ->
                currentState.copy(
                    cars = cars
                )
            }
            cars.forEach {
                addNewCarToDb(it).await()
            }
        }
    }

    fun addNewCarToDb(carView: CarView) : Deferred<Unit> {
        return viewModelScope.async(Dispatchers.IO) {
            addNewCarToDbUseCase(carView)
        }
    }


    fun getAllCarsOutDb() {
        viewModelScope.launch {
            val cars = getAllCarsInDbUseCase()
            _viewState.update { currentState ->
                currentState.copy(
                    cars = cars,
                )
            }
            Log.d("VM-getAllCarsOutDb", cars.toString())
        }
    }

    fun getAllSubscriptionsOutDb() {
        viewModelScope.launch {
            val sub = getAllSubscriptionInDbUseCase()
            if (sub.isNotEmpty()) {
                val lastSubscription = sub.size.minus(1)
                _viewState.update { currentState ->
                    currentState.copy(
                        subscriptionInfo = sub[lastSubscription],
                    )
                }
            }
        }
    }

    fun setCurrentItem(item: CarView) {
        _viewState.update { currentState ->
            currentState.copy(
                currentCar = item
            )
        }
    }


    fun buyTestSubscription() {
        _viewState.update { state ->
            state.copy(
                subscriptionInfo = state.subscriptionInfo?.copy(
                    isUnlimitedSubscription = true,
                    end = state.subscriptionInfo.start?.plus((1000 * 60))
                )
            )
        }
        updateSubscriptionInDb()
    }


    fun checkSubscriptionInDb(id: Int) {
        viewModelScope.launch {
            val res = checkSubInDbUseCase(id)
            Log.d("VM-checkSubscriptionInDb", res.toString())

            _viewState.update { currentState ->
                currentState.copy(
                    subscriptionInfo = res
                )
            }

        }
    }

    fun deleteSubscription() {
        _viewState.update { state ->
            state.copy(
                subscriptionInfo = state.subscriptionInfo?.copy(
                    isUnlimitedSubscription = false,
                )
            )
        }
        updateSubscriptionInDb()
    }

    fun decSubscriptionWatchCount() {
        viewModelScope.launch {
            _viewState.update { state ->
                state.copy(
                    subscriptionInfo = state.subscriptionInfo?.copy(
                        watchCarsCount = state.subscriptionInfo.watchCarsCount?.dec()
                    )
                )
            }
            updateSubscriptionInDb()
        }
    }

    fun decAddToFavoriteCount() {
        viewModelScope.launch {
            _viewState.update { state ->
                state.copy(
                    subscriptionInfo = state.subscriptionInfo?.copy(
                        addToFavoriteCount = state.subscriptionInfo.addToFavoriteCount?.dec()
                    )
                )
            }
        }

    }

    fun clearDb(): Deferred<Unit> {
        return viewModelScope.async(Dispatchers.IO) {
            resetAppUseCase()
            _viewState.update { currentState ->
                currentState.copy(
                    isNeedData = true
                )
            }

        }
    }

    fun sendSnackbar(scaffoldState: ScaffoldState, message: String) {
        viewModelScope.launch {
            scaffoldState.snackbarHostState.showSnackbar(message)

        }
    }


}