package com.inis.acaziatest.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

fun EditText.onTextInput(onTextChanged: ((String) -> Unit)?, afterTextChanged: ((String) -> Unit)?) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            onTextChanged?.let { it(s.toString().trim()) }
        }

        override fun afterTextChanged(s: Editable?) {
            afterTextChanged?.let { it(s.toString().trim()) }
        }
    })
}