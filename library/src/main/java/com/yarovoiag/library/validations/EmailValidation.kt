package com.yarovoiag.library.validations

import android.content.Context
import android.util.Patterns
import androidx.annotation.StringRes
import com.yarovoiag.library.R
import com.yarovoiag.library.Validation
import com.yarovoiag.library.ValidationResult
import com.yarovoiag.library.validations.EmailValidation.Builder
import com.yarovoiag.library.validations.EmailValidation.Companion.build

/**
 * Email validation [Patterns.EMAIL_ADDRESS]. Use [build] to create validation with default settings.
 * Use [Builder] to setup different settings.
 *
 * @param error Error message if email is not valid.
 */
class EmailValidation(private val error: String) : Validation {

    companion object {
        /**
         * Create validation with defaults params. Use [Builder] for customization
         */
        fun build(context: Context) = Builder(context).build()
    }

    override fun validate(field: String?) = ValidationResult(
        when {
            field == null -> error
            field.matches(Patterns.EMAIL_ADDRESS.toRegex()).not() -> error
            else -> ""
        }
    )

    class Builder(context: Context) : BaseBuilder(context) {

        private var error = getString(R.string.email_is_invalid)

        fun error(message: String?) = apply { error = message.orEmpty() }
        fun error(@StringRes id: Int) = apply { error = getString(id) }

        fun build() = EmailValidation(error)
    }
}