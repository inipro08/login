package com.inis.acaziatest.shareviewmodel

import androidx.lifecycle.MutableLiveData
import com.inis.acaziatest.data.repository.SharedPrefRepository
import com.inis.acaziatest.ui.base.BaseViewModel
import javax.inject.Inject

class MainShareViewModel @Inject constructor(private val sharedPrefRepository: SharedPrefRepository) :
    BaseViewModel() {
    var pass = MutableLiveData<String>()
    var phoneNumber = MutableLiveData<Int>()
    var savePassSuccess = sharedPrefRepository.getLoginSuccess()

    fun savePassUser(passWord: String) {
        addDisposable(
            sharedPrefRepository.savePass(passWord).subscribeOn(scheduleProviders.io())
                .observeOn(scheduleProviders.ui()).subscribe({

                }, {})
        )
    }


    fun getPassUser() = addDisposable(
        sharedPrefRepository.getPass().subscribeOn(scheduleProviders.io())
            .observeOn(scheduleProviders.ui()).subscribe({  pass.value = it?.toString() ?: ""}, {})
    )

    fun savePhoneUser(it: Int) {
        addDisposable(
            sharedPrefRepository.savePhone(it).subscribeOn(scheduleProviders.io())
                .observeOn(scheduleProviders.ui()).subscribe({}, {})
        )
    }

    fun getPhoneNumber() = addDisposable(
        sharedPrefRepository.getPhone().subscribeOn(scheduleProviders.io())
            .observeOn(scheduleProviders.ui()).subscribe({
                phoneNumber.value = it ?: 0
            }, {})
    )

    fun loginSuccess(isLoginSuccess: Boolean) {
        addDisposable(
            sharedPrefRepository.loginSuccess(isLoginSuccess).subscribeOn(scheduleProviders.io())
                .observeOn(scheduleProviders.ui()).subscribe({
                }, {})
        )
    }
}