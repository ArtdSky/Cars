package com.example.cars.presentation.di

import com.example.cars.data.repository.RepositoryImpl
import com.example.cars.data.storage.CarsStorage
import com.example.cars.data.storage.SubscriptionStorage
import com.example.cars.data.storage.local.AppDatabase
import com.example.cars.data.storage.local.AppRepository
import com.example.cars.domain.repository.CarsRepository
import com.example.cars.domain.repository.ResetRepository
import com.example.cars.domain.repository.SubscriptionRepository
import com.example.cars.domain.usecases.ResetAppUseCase
import com.example.cars.domain.usecases.cars.AddNewCarToDbUseCase
import com.example.cars.domain.usecases.cars.GetAllCarsInDbUseCase
import com.example.cars.domain.usecases.cars.GetCarByIdInDbUseCase
import com.example.cars.domain.usecases.cars.SortCarsFromDbUseCase
import com.example.cars.domain.usecases.subscription.AddNewSubscriptionUseCase
import com.example.cars.domain.usecases.subscription.CheckSubInDbUseCase
import com.example.cars.domain.usecases.subscription.GetAllSubscriptionInDbUseCase
import com.example.cars.presentation.viewmodel.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * dependency injection с помощью Koin
 */
val DependencyInjection = module {

    single {
        AppDatabase.getDatabase(androidContext())
    }
    single { get<AppDatabase>().carsDao() }
    single { get<AppDatabase>().subscribeDao() }

    single { AppRepository(get(), get()) }
    single { CarsStorage(get()) }
    single { SubscriptionStorage(get()) }

    single<CarsRepository> { RepositoryImpl(get(), get()) }
    single<SubscriptionRepository> { RepositoryImpl(get(), get()) }
    single<ResetRepository> { RepositoryImpl(get(), get()) }

    factory { AddNewCarToDbUseCase(get()) }
    factory { GetAllCarsInDbUseCase(get()) }
    factory { GetCarByIdInDbUseCase(get()) }
    factory { SortCarsFromDbUseCase(get()) }
    factory { AddNewSubscriptionUseCase(get()) }
    factory { CheckSubInDbUseCase(get()) }
    factory { ResetAppUseCase(get()) }
    factory { GetAllSubscriptionInDbUseCase(get()) }

    viewModel {
        MainViewModel(
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get()
        )
    }

}