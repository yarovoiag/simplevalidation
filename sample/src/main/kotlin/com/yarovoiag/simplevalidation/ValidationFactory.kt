package com.yarovoiag.simplevalidation

import android.content.Context
import com.yarovoiag.library.ValidationSet
import com.yarovoiag.library.validations.*

/**
 * Sample of using [com.yarovoiag.library.Validation] and [ValidationSet]
 */
class ValidationFactory private constructor() {
    companion object {
        /**
         * ValidationSet sample. Order is important.
         */
        fun password(context: Context) = ValidationSet(
            empty(context),
            passwordLength(context),
            passwordLower(context),
            passwordUpper(context),
            passwordDigits(context),
            passwordSymbols(context)
        )

        fun nickname(context: Context) = ValidationSet(empty(context), labelLength(context))

        fun empty(context: Context) = EmptyValidation.Builder(context)
            .error(R.string.required_field_error)
            .build()

        fun email(context: Context) = EmailValidation.Builder(context)
            .error(R.string.email_error)
            .build()

        fun labelLength(context: Context) = LengthValidation.Builder(context)
            .minLength(context.resources.getInteger(R.integer.min_label_length))
            .maxLength(context.resources.getInteger(R.integer.max_label_length))
            .minLengthError(R.string.min_length_error)
            .maxLengthError(R.string.max_length_error)
            .build()

        fun passwordLength(context: Context) = LengthValidation.Builder(context)
            .minLength(context.resources.getInteger(R.integer.min_password_length))
            .maxLength(context.resources.getInteger(R.integer.max_password_length))
            .minLengthError(R.string.min_length_password_error)
            .maxLengthError(R.string.max_length_password_error)
            .build()

        fun passwordLower(context: Context) = LowerCaseValidation.Builder(context)
            .error(R.string.lower_password_error)
            .build()

        fun passwordUpper(context: Context) = UpperCaseValidation.Builder(context)
            .error(R.string.upper_password_error)
            .build()

        fun passwordDigits(context: Context) = DigitsValidation.Builder(context)
            .error(R.string.digits_password_error)
            .build()

        fun passwordSymbols(context: Context) = SymbolsValidation.Builder(context)
            .error(R.string.symbols_password_error)
            .build()

    }
}