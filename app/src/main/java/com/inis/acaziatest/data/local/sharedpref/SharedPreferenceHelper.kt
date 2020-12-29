package com.inis.acaziatest.data.local.sharedpref

import io.reactivex.Completable
import io.reactivex.Single

interface SharedPreferenceHelper {
    fun putString(key: String, value: String): Completable

    fun putBoolean(key: String, value: Boolean): Completable

    fun getString(key: String, defaultValue: String = ""): Single<String>

    fun getInt(key: String, defaultValue: Int = 0): Single<Int>

    fun putInt(key: String, value: Int) : Completable

    fun getBoolean(key: String, defaultValue: Boolean = false): Boolean

    fun putLong(key: String, value: Long)

    fun getLong(key: String, defaultValue: Long): Long
}
