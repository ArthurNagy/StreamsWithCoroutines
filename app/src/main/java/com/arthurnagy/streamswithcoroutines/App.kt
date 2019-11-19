package com.arthurnagy.streamswithcoroutines

import android.app.Application
import com.arthurnagy.streamswithcoroutines.api.UserService
import com.arthurnagy.streamswithcoroutines.api.UserServiceImpl
import com.arthurnagy.streamswithcoroutines.repository.user.UserLocalSource
import com.arthurnagy.streamswithcoroutines.repository.user.UserRemoteSource
import com.arthurnagy.streamswithcoroutines.repository.user.UserRepository
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(appModule)
        }
    }

    companion object {
        private val appModule = module {
            factory<UserService> { UserServiceImpl() }
            factory { UserRemoteSource(userService = get()) }
            single { UserLocalSource() }
            single { UserRepository(remoteSource = get(), localSource = get()) }

            viewModel { UserViewModel(userRepository = get()) }
        }
    }
}