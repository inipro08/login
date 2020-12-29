package com.inis.acaziatest.ui.base

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import com.inis.acaziatest.shareviewmodel.MainShareViewModel

abstract class BaseSplashFragment<ViewBinding : ViewDataBinding, ViewModel : BaseSplashViewModel> :
    BaseFragment<ViewBinding, ViewModel>() {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initData()
        observeLiveData()
    }

    private fun initData() {
    }

    private fun observeLiveData() {

    }

    abstract fun gotoLoginFragment()
    abstract val sharedViewModel: MainShareViewModel
}