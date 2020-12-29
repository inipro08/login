package com.inis.acaziatest.ui.base

import androidx.fragment.app.Fragment
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity : DaggerAppCompatActivity() {

    fun addFragment(
        container: Int,
        fragment: Fragment,
        tag: String,
        isAddBackstack: Boolean = false
    ) {
        supportFragmentManager.beginTransaction().apply {
            add(container, fragment, tag)
            if (isAddBackstack) {
                addToBackStack(null)
            }
            commit()
        }
    }


    fun replaceFragment(
        container: Int,
        fragment: Fragment,
        tag: String,
        isAddBackstack: Boolean = false
    ) {
        supportFragmentManager.beginTransaction().apply {
            replace(container, fragment, tag)
            if (isAddBackstack) {
                addToBackStack(null)
            }
            commit()
        }
    }
}