package com.inis.acaziatest.di.builder

import android.app.Application
import android.content.Context
import com.inis.acaziatest.rx.AppSchedulerProvider
import com.inis.acaziatest.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class, NetworkModule::class, RepositoryModule::class])
class AppModule {
    @Singleton
    @Provides
    fun providerContext(application: Application): Context {
        return application
    }

    @Singleton
    @Provides
    fun providerSchedulerProvider(): SchedulerProvider {
        return AppSchedulerProvider()
    }
}
