package com.inis.acaziatest

import android.app.Activity
import android.app.Application
import android.content.Context
import com.inis.acaziatest.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class Application : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>
    override fun activityInjector(): AndroidInjector<Activity> = dispatchingAndroidInjector

    override fun onCreate() {
        super.onCreate()
        context = this
        DaggerAppComponent.builder().application(this).build().inject(this)
    }

    companion object {
        var context: Context? = null
        fun getAppContext(): Context? {
            return context
        }
    }
}