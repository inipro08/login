package com.inis.acaziatest.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.inis.acaziatest.rx.SchedulerProvider
import com.inis.acaziatest.data.base.BaseException
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

abstract class BaseViewModel : ViewModel() {

    val isLoading = MutableLiveData<Boolean>().apply { value = false }
    val errorMessage = MutableLiveData<String>()

    @Inject
    lateinit var scheduleProviders: SchedulerProvider

    // rx
    val compositeDisposable = CompositeDisposable()

    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    open fun onLoadFail(throwable: Throwable) {
        try {
            when (throwable) {
                is BaseException -> {
                    when (throwable.serverErrorCode) {
                        // custom server error code
                        else -> {
                            when (throwable.cause) {
                                is UnknownHostException -> {
                                    errorMessage.value = "No Internet Connection"
                                }
                                is SocketTimeoutException -> {
                                    errorMessage.value = "Connect timeout, please retry"
                                }
                                else -> {
                                    errorMessage.value = throwable.message
                                }
                            }
                        }
                    }
                }
                else -> {
                    errorMessage.value = throwable.message
                }
            }
        } catch (e: Exception) {
            errorMessage.value = throwable.message
        }
        isLoading.value = false
    }

    open fun showError(e: Throwable) {
        errorMessage.value = e.message
    }

    fun onActivityDestroyed() {
        compositeDisposable.clear()
    }
}