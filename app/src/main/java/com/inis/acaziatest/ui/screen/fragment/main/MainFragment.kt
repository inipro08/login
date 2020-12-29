package com.inis.acaziatest.ui.screen.fragment.main

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.inis.acaziatest.R
import com.inis.acaziatest.databinding.FragmentMainBinding
import com.inis.acaziatest.shareviewmodel.MainShareViewModel
import com.inis.acaziatest.ui.adapter.MainPagerAdapter
import com.inis.acaziatest.ui.base.BaseFragment

class MainFragment : BaseFragment<FragmentMainBinding, MainViewModel>(), View.OnClickListener {

    companion object {
        const val TAG = "MainFragment"
        fun newInstance() = MainFragment()
    }

    override val viewModel: MainViewModel
        get() = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
    override val layoutId: Int = R.layout.fragment_main

    private lateinit var mainPagerAdapter: MainPagerAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews()
        setListener()
    }

    private fun setListener() {
        viewBinding.apply {
        }
    }

    private fun initViews() {
        mainPagerAdapter = MainPagerAdapter(childFragmentManager, lifecycle)
        viewBinding.apply {
            viewPagerMain.apply {
                adapter = mainPagerAdapter
                isUserInputEnabled = false
                offscreenPageLimit = 3
            }
        }
    }

    override fun onClick(p0: View?) {
        viewBinding.apply {

        }
    }
}