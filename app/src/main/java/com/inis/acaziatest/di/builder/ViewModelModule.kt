package com.inis.acaziatest.di.builder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.inis.acaziatest.shareviewmodel.MainShareViewModel
import com.inis.acaziatest.ui.screen.fragment.home.HomeViewModel
import com.inis.acaziatest.ui.screen.fragment.main.MainViewModel
import com.inis.acaziatest.ui.screen.fragment.one.OneViewModel
import com.inis.acaziatest.ui.screen.fragment.splash.SplashViewModel
import com.inis.acaziatest.ui.screen.login.LoginViewModel
import com.inis.acaziatest.utils.ViewModelProviderFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(
        providerFactory: ViewModelProviderFactory
    ): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainShareViewModel::class)
    abstract fun bindMainSharedViewModel(mainShareViewModel: MainShareViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewMode: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun bindSplashViewModel(splashViewModel: SplashViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeVM(homeViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(OneViewModel::class)
    abstract fun bindOneVM(oneViewModel: OneViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginVM(loginVM: LoginViewModel): ViewModel
}
