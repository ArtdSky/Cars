package com.example.cars

import android.app.Application
import com.example.cars.presentation.di.DependencyInjection
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * overrides the onCreate() method to initialize the Koin dependency injection framework
 * by calling the startKoin() method
 */
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(listOf( DependencyInjection) )
        }
    }
}