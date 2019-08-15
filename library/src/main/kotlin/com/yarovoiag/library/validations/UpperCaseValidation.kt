package com.yarovoiag.library.validations

import android.content.Context
import androidx.annotation.StringRes
import com.yarovoiag.library.R
import com.yarovoiag.library.Validation
import com.yarovoiag.library.ValidationResult
import com.yarovoiag.library.validations.UpperCaseValidation.Builder
import com.yarovoiag.library.validations.UpperCaseValidation.Companion.build

/**
 * Uppercase validation.
 * Use [build] to create validation with default settings.
 * Use [Builder] to setup different settings.
 *
 * @param error Error message if validation field doesn't contains uppercase characters.
 */
class UpperCaseValidation(private val error: String) : Validation {

    companion object {
        /**
         * Create validation with defaults params. Use [Builder] for customization
         */
        fun build(context: Context) = Builder(context).build()
    }

    override fun validate(field: String?) = ValidationResult(
            when {
                field.isNullOrEmpty() -> error
                containsUpperCase(field).not() -> error
                else -> ""
            }
    )

    class Builder(context: Context) : BaseBuilder(context) {

        private var error = getString(R.string.default_error)

        fun error(message: String?) = apply { error = message.orEmpty() }
        fun error(@StringRes id: Int) = apply { error = getString(id) }


        fun build() = UpperCaseValidation(error)
    }
}