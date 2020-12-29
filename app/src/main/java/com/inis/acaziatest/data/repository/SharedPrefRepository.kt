package com.inis.acaziatest.data.repository

import io.reactivex.Completable
import io.reactivex.Single

interface SharedPrefRepository {

    fun savePass(passWord: String): Completable

    fun getPass(): Single<String>

    fun savePhone(passWord: Int): Completable

    fun getPhone(): Single<Int>

    fun loginSuccess(loginSuccess: Boolean): Completable

    fun getLoginSuccess() : Boolean
}
