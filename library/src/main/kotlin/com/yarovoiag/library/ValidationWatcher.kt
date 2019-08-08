package com.yarovoiag.library

import android.text.Editable
import android.text.TextWatcher
import com.google.android.material.textfield.TextInputLayout
import java.lang.ref.WeakReference

/**
 * [TextWatcher] wrapper with validation.
 * @param til [TextInputLayout] where validation error should be shown
 * @param validation [Validation]
 */
open class ValidationWatcher(til: TextInputLayout, validation: Validation) : TextWatcher {

    private val tilWR: WeakReference<TextInputLayout> = WeakReference(til)
    private val validation: Validation? = validation

    override fun afterTextChanged(s: Editable?) = Unit

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        s?.toString()?.let { validate(it) }
    }

    fun validate(text: String) {
        tilWR.get()?.error = validation?.validate(text)?.takeUnless { it.isValid }?.error
    }
}