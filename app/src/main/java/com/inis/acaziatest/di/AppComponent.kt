package com.inis.acaziatest.di

import com.inis.acaziatest.di.builder.ActivityModule
import com.inis.acaziatest.di.builder.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [(AndroidInjectionModule::class), (AppModule::class), (ActivityModule::class)])
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: android.app.Application): Builder

        fun build(): AppComponent
    }

    fun inject(application: com.inis.acaziatest.Application)

}