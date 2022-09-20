package com.example.books.activities

import android.app.Application
import com.example.books.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                PresentationModule,
                dataModule,
                dataRemote,
                dataLocalModule,
                databaseModule,
                domainModule
            ).androidContext(applicationContext)
        }

    }
}