package com.inis.acaziatest.data.repository

import com.inis.acaziatest.data.local.sharedpref.SharedPreferenceHelper
import com.inis.acaziatest.utils.Constants
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class SharedPrefRepositoryImpl @Inject constructor(private val helper: SharedPreferenceHelper) :
    SharedPrefRepository {
    override fun savePass(passWord: String): Completable {
        return helper.putString(Constants.PASSWORD, passWord)
    }

    override fun getPass(): Single<String> {
        return helper.getString(Constants.PASSWORD, "")
    }

    override fun savePhone(phoneNumber: Int): Completable {
        return helper.putInt(Constants.KEY_PHONE, phoneNumber)
    }

    override fun getPhone(): Single<Int> {
        return helper.getInt(Constants.KEY_PHONE, 0)
    }

    override fun loginSuccess(loginSuccess: Boolean): Completable {
        return helper.putBoolean(Constants.LOGIN_SUCCESS, loginSuccess)
    }

    override fun getLoginSuccess(): Boolean {
        return helper.getBoolean(Constants.LOGIN_SUCCESS, false)
    }
}
