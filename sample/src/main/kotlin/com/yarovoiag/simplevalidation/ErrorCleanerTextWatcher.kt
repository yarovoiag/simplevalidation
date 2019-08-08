package com.yarovoiag.simplevalidation

import android.text.Editable
import android.text.TextWatcher
import com.google.android.material.textfield.TextInputLayout
import java.lang.ref.WeakReference

class ErrorCleanerTextWatcher(til: TextInputLayout) : TextWatcher {
    private val tilWR = WeakReference(til)
    override fun afterTextChanged(s: Editable?) = Unit
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        tilWR.get()?.error = null
    }
}