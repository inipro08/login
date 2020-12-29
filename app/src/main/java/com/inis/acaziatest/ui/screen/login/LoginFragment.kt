package com.inis.acaziatest.ui.screen.login

import android.os.Bundle
import android.telephony.PhoneNumberUtils
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.inis.acaziatest.R
import com.inis.acaziatest.databinding.LoginBinding
import com.inis.acaziatest.ui.base.BaseFragment
import com.inis.acaziatest.ui.screen.fragment.main.MainFragment
import com.inis.acaziatest.utils.onTextInput

class LoginFragment : BaseFragment<LoginBinding, LoginViewModel>() {

    companion object {
        const val TAG = "LoginFragment"
    }

    override val viewModel: LoginViewModel
        get() = ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)
    override val layoutId: Int
        get() = R.layout.fragment_login


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        PhoneNumberUtils.formatNumber(viewBinding.edPhone.text.toString())
        mainShareViewModel.phoneNumber.observe(viewLifecycleOwner, {
            if (it != 0) viewBinding.edPhone.setText(it.toString())
        })
        viewBinding.apply {
            edPhone.onTextInput(onTextChanged = null, afterTextChanged = {
                mainShareViewModel.savePhoneUser(it.toInt())
            })
            edPassword.onTextInput(onTextChanged = {
                if (it.length >= 8) mainShareViewModel.savePassUser(it)
                else Toast.makeText(
                    context,
                    "Password must be greater than 8 characters",
                    Toast.LENGTH_SHORT
                ).show()
            }, afterTextChanged = null)
            buttonLogin.setOnClickListener {
                if (edPhone.text.toString().isNotEmpty() && edPassword.text.toString().isNotEmpty()) {
                    gotoMainFragment()
                    mainShareViewModel.loginSuccess(false)
                }
                else Toast.makeText(context, "Phone number and password cannot be left blank", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun gotoMainFragment() {
        replaceFragment(
            fragment = MainFragment.newInstance(),
            addToBackStack = false,
            TAG = MainFragment.TAG,
            transit = TransitionFrom.LEFT
        )
    }
}