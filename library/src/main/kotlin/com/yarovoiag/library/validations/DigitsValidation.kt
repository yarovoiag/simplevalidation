package com.yarovoiag.library.validations

import android.content.Context
import androidx.annotation.StringRes
import com.yarovoiag.library.R
import com.yarovoiag.library.Validation
import com.yarovoiag.library.ValidationResult
import com.yarovoiag.library.validations.DigitsValidation.Builder
import com.yarovoiag.library.validations.DigitsValidation.Companion.build

/**
 * Digit validation. Use [build] to create validation with default settings.
 * Use [Builder] to setup different settings.
 *
 * @param error Error message if validation field doesn't contains digits
 */
class DigitsValidation(private val error: String) : Validation {

    companion object {
        /**
         * Create validation with defaults params. Use [Builder] for customization
         */
        fun build(context: Context) = Builder(context).build()
    }

    override fun validate(field: String?) = ValidationResult(
        when {
            field.isNullOrEmpty() -> error
            containsDigits(field).not() -> error
            else -> ""
        }
    )

    class Builder(context: Context) : BaseBuilder(context) {

        private var error = getString(R.string.default_error)

        fun error(message: String?) = apply { error = message.orEmpty() }
        fun error(@StringRes id: Int) = apply { error = getString(id) }

        fun build() = DigitsValidation(error)
    }
}