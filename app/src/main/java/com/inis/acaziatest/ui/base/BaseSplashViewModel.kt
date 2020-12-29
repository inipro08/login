package com.inis.acaziatest.ui.base

import com.inis.acaziatest.data.repository.SharedPrefRepository
import javax.inject.Inject

abstract class BaseSplashViewModel : BaseViewModel() {

    @Inject
    lateinit var sharedPrefRepository: SharedPrefRepository
}