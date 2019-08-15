package com.yarovoiag.library.validations

import android.content.Context
import androidx.annotation.StringRes
import com.yarovoiag.library.R
import com.yarovoiag.library.Validation
import com.yarovoiag.library.ValidationResult
import com.yarovoiag.library.validations.RegexValidation.Builder
import java.util.regex.Pattern

/**
 * Regex validation.
 * Use [build] to create validation with default settings.
 * Use [Builder] to setup different settings.
 *
 * @param regex Custom [Regex] for matching
 * @param error Error message if validation field doesn't match [regex]
 */
class RegexValidation(private val regex: Regex,
                      private val error: String) : Validation {

    companion object {
        /**
         * Create validation with defaults params. Use [Builder] for customization
         */
        fun build(context: Context) = Builder(context).build()
    }

    override fun validate(field: String?) = ValidationResult(
            when {
                field == null -> error
                field.matches(regex).not() -> error
                else -> ""
            }
    )

    class Builder(context: Context) : BaseBuilder(context) {

        private var regex = ANY_REGEX
        private var error = getString(R.string.default_error)

        fun regex(regex: String) = apply { this.regex = regex.toRegex() }
        fun regex(regex: Regex) = apply { this.regex = regex }
        fun regex(regex: Pattern) = apply { this.regex = regex.toRegex() }

        fun error(message: String?) = apply { error = message.orEmpty() }
        fun error(@StringRes id: Int) = apply { error = getString(id) }


        fun build() = RegexValidation(regex, error)
    }
}