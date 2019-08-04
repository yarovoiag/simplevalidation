package com.yarovoiag.library.validations

import android.content.Context
import androidx.annotation.StringRes
import com.yarovoiag.library.R
import com.yarovoiag.library.Validation
import com.yarovoiag.library.ValidationResult
import com.yarovoiag.library.validations.SymbolsValidation.Builder
import com.yarovoiag.library.validations.SymbolsValidation.Companion.build

/**
 * Symbols validation.
 * Use [build] to create validation with default settings.
 * Use [Builder] to setup different settings.
 *
 * @param error Error message if validation field doesn't contains symbols characters.  !"#$%&'()*+,-./:;<=>?@[\]^_`{|}~
 */
class SymbolsValidation(private val error: String) : Validation {

    companion object {
        /**
         * Create validation with defaults params. Use [Builder] for customization
         */
        fun build(context: Context) = Builder(context).build()
    }

    override fun validate(field: String?) = ValidationResult(
            when {
                field.isNullOrEmpty() -> error
                containsSymbol(field).not() -> error
                else -> ""
            }
    )

    class Builder(context: Context) : BaseBuilder(context) {

        private var error = getString(R.string.field_must_contains_symbols)

        fun error(message: String?) = apply { error = message.orEmpty() }
        fun error(@StringRes id: Int) = apply { error = getString(id) }


        fun build() = SymbolsValidation(error)
    }
}