package com.yarovoiag.library.validations

import android.content.Context
import androidx.annotation.StringRes
import com.yarovoiag.library.R
import com.yarovoiag.library.Validation
import com.yarovoiag.library.ValidationResult
import com.yarovoiag.library.validations.LengthValidation.Builder
import com.yarovoiag.library.validations.LengthValidation.Companion.build

/**
 * Length validation.
 * Use [build] to create validation with default settings.
 * Use [Builder] to setup different settings.
 *
 * @param minLength Minimum field length
 * @param maxLength Maximum field length
 * @param minLengthError Error message if validation field length less than [minLength]
 * @param maxLengthError Error message if validation field length more than [maxLength]
 */
class LengthValidation(private val minLength: Int,
                       private val maxLength: Int,
                       private val minLengthError: String,
                       private val maxLengthError: String) : Validation {

    companion object {
        /**
         * Create validation with defaults params. Use [Builder] for customization
         */
        fun build(context: Context) = Builder(context).build()
    }

    override fun validate(field: String?) = ValidationResult(
            when {
                field == null -> minLengthError
                field.length < minLength -> minLengthError
                field.length > maxLength -> maxLengthError
                else -> ""
            }
    )

    class Builder(context: Context) : BaseBuilder(context) {

        private var minLength = getInteger(R.integer.min_field_length)
        private var maxLength = getInteger(R.integer.max_field_length)

        private var minLengthError = getString(R.string.default_error)
        private var maxLengthError = getString(R.string.default_error)

        fun minLength(length: Int) = apply { minLength = length }
        fun maxLength(length: Int) = apply { maxLength = length }

        fun minLengthError(message: String?) = apply { minLengthError = message.orEmpty() }
        fun minLengthError(@StringRes id: Int) = apply { minLengthError = getString(id) }

        fun maxLengthError(message: String?) = apply { maxLengthError = message.orEmpty() }
        fun maxLengthError(@StringRes id: Int) = apply { maxLengthError = getString(id) }


        fun build(): LengthValidation {
            val min = Math.min(minLength, maxLength)
            val max = Math.max(minLength, maxLength)
            return LengthValidation(min, max, minLengthError, maxLengthError)
        }
    }

}