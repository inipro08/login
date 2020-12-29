package com.inis.acaziatest.ui.screen.fragment.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.lifecycle.ViewModelProvider
import com.inis.acaziatest.R
import com.inis.acaziatest.databinding.FragmentSplashBinding
import com.inis.acaziatest.shareviewmodel.MainShareViewModel
import com.inis.acaziatest.ui.base.BaseSplashFragment
import com.inis.acaziatest.ui.screen.fragment.main.MainFragment
import com.inis.acaziatest.ui.screen.login.LoginFragment

class SplashFragment : BaseSplashFragment<FragmentSplashBinding, SplashViewModel>() {

    companion object {
        const val TAG = "SplashFragment"
    }

    override val viewModel: SplashViewModel
        get() = ViewModelProvider(this, viewModelFactory)[SplashViewModel::class.java]
    override val layoutId: Int
        get() = R.layout.fragment_splash

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews()
        mainShareViewModel.run {
            getPassUser()
            getPhoneNumber()
        }
    }

    private fun initViews() {
        val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.enter_from_bottom)
        animation.duration = 700
        viewBinding.nameApp.startAnimation(animation)
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {}

            override fun onAnimationRepeat(p0: Animation?) {}

            override fun onAnimationEnd(p0: Animation?) {
                mainShareViewModel.pass.observe(viewLifecycleOwner, {
                    if (it == "" || mainShareViewModel.savePassSuccess) gotoLoginFragment()
                    else gotoMainFragment()
                })
            }
        })
    }

    override fun gotoLoginFragment() {
        replaceFragment(
            fragment = LoginFragment(),
            addToBackStack = false,
            TAG = LoginFragment.TAG,
            transit = TransitionFrom.LEFT
        )
    }

    private fun gotoMainFragment() {
        replaceFragment(
            fragment = MainFragment.newInstance(),
            addToBackStack = false,
            TAG = MainFragment.TAG,
            transit = TransitionFrom.LEFT
        )
    }


    override val sharedViewModel: MainShareViewModel
        get() = ViewModelProvider(
            requireActivity(),
            viewModelFactory
        ).get(MainShareViewModel::class.java)

}
