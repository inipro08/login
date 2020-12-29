package com.inis.acaziatest.di.builder

import com.google.gson.Gson
import com.inis.acaziatest.data.local.sharedpref.SharedPreferenceHelper
import com.inis.acaziatest.data.local.sharedpref.SharedPreferences
import com.inis.acaziatest.data.repository.AppRepository
import com.inis.acaziatest.data.repository.AppRepositoryImpl
import com.inis.acaziatest.data.repository.SharedPrefRepository
import com.inis.acaziatest.data.repository.SharedPrefRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun providerGSon(): Gson = Gson()

    @Provides
    @Singleton
    fun provideAppDataRepository(appRepositoryImpl: AppRepositoryImpl): AppRepository =
        appRepositoryImpl

    @Provides
    @Singleton
    fun providePrefHelper(appPrefs: SharedPreferences): SharedPreferenceHelper {
        return appPrefs
    }

    @Provides
    @Singleton
    fun provideSharedPreferenceRepository(sharedPrefRepositoryImpl: SharedPrefRepositoryImpl): SharedPrefRepository =
        sharedPrefRepositoryImpl
}
