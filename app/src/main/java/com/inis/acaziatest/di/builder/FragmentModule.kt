package com.inis.acaziatest.di.builder

import com.inis.acaziatest.ui.screen.fragment.home.HomeFragment
import com.inis.acaziatest.ui.screen.fragment.main.MainFragment
import com.inis.acaziatest.ui.screen.fragment.one.OneFragment
import com.inis.acaziatest.ui.screen.fragment.splash.SplashFragment
import com.inis.acaziatest.ui.screen.login.LoginFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun contributeMainFragment(): MainFragment

    @ContributesAndroidInjector
    abstract fun contributeSplashFragment(): SplashFragment

    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributeOneFragment(): OneFragment

    @ContributesAndroidInjector
    abstract fun contributeLoginFragment(): LoginFragment
}
