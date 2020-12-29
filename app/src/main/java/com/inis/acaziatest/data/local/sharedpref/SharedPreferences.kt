package com.inis.acaziatest.data.local.sharedpref

import android.content.Context
import com.google.gson.Gson
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class SharedPreferences @Inject constructor(val context: Context, val gson: Gson) :
    SharedPreferenceHelper {

    private val sharedPreferences =
        context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)

    override fun putString(key: String, value: String): Completable {
        return Completable.fromAction {
            sharedPreferences.edit().putString(key, value).apply()
        }
    }

    override fun putBoolean(key: String, value: Boolean): Completable {
        return Completable.fromAction {
            sharedPreferences.edit().putBoolean(key, value).apply()
        }
    }

    override fun getString(key: String, defaultValue: String): Single<String> {
        return Single.fromCallable {
            sharedPreferences.getString(key, defaultValue)
        }
    }

    override fun getInt(key: String, defaultValue: Int): Single<Int> {
        return Single.fromCallable {
            sharedPreferences.getInt(key, defaultValue)
        }
    }

    override fun putInt(key: String, value: Int): Completable {
        return Completable.fromAction {
            sharedPreferences.edit().putInt(key, value).apply()
        }
    }

    override fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, defaultValue)
    }

    override fun getLong(key: String, defaultValue: Long): Long {
        return sharedPreferences.getLong(key, defaultValue)
    }

    override fun putLong(key: String, value: Long) {
        sharedPreferences.edit().putLong(key, value).apply()
    }
}