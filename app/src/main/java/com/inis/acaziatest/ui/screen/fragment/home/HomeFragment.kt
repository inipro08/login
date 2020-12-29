package com.inis.acaziatest.ui.screen.fragment.home

import androidx.lifecycle.ViewModelProvider
import com.inis.acaziatest.R
import com.inis.acaziatest.databinding.FragmentHomeBinding
import com.inis.acaziatest.ui.base.BaseFragment

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override val viewModel: HomeViewModel
        get() = ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)
    override val layoutId: Int
        get() = R.layout.fragment_home
}