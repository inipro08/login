package com.inis.acaziatest.ui.screen.activity.main

import android.os.Bundle
import android.os.SystemClock
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.inis.acaziatest.ui.base.BaseActivity
import com.inis.acaziatest.ui.screen.fragment.main.MainFragment
import com.inis.acaziatest.ui.screen.fragment.splash.SplashFragment
import com.inis.acaziatest.R
import javax.inject.Inject

class MainActivity : BaseActivity() {
    var lastBackPress: Long = 0
    var toastBackPress: Toast? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toastBackPress = Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT)
        addFragment(
            R.id.main_activity_container,
            SplashFragment(),
            SplashFragment.TAG,
            false
        )
    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.main_activity_container)
        if (fragment is MainFragment) {
            if (SystemClock.elapsedRealtime() - lastBackPress < 2000) {
                super.onBackPressed()
                toastBackPress?.cancel()
                return
            }
            toastBackPress?.show()
            lastBackPress = SystemClock.elapsedRealtime()
            return
        }
        super.onBackPressed()
    }
}