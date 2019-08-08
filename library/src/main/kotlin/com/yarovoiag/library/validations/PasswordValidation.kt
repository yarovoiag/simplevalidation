//package com.yarovoiag.library.validations
//
//import android.content.Context
//import androidx.annotation.StringRes
//import com.yarovoiag.library.R
//import com.yarovoiag.library.Validation
//import com.yarovoiag.library.ValidationResult
//import com.yarovoiag.library.validations.PasswordValidation.Builder
//
///**
// * Password validation. You can create you [ValidationSet] with custom validations.
// * Use [build] to create validation with default settings.
// * Use [Builder] to setup different settings.
// *
// * @param minLength Minimum field length
// * @param maxLength Maximum field length
// * @param lowerCase true - lowercase validation will be used
// * @param upperCase true - uppercase validation will be used
// * @param digits true - digits validation will be used
// * @param symbols true - symbols validation will be used
// * @param minLengthError Error message if validation field length less than [minLength]
// * @param maxLengthError Error message if validation field length more than [maxLength]
// * @param lowerCaseError Error message if validation field doesn't contains lowercase characters.
// * @param upperCaseError Error message if validation field doesn't contains uppercase characters.
// * @param digitsError Error message if validation field doesn't contains digits
// * @param symbolError message if validation field doesn't contains symbols characters.  !"#$%&'()*+,-./:;<=>?@[\]^_`{|}~
// */
//class PasswordValidation(private val minLength: Int,
//                         private val maxLength: Int,
//                         private val lowerCase: Boolean,
//                         private val upperCase: Boolean,
//                         private val digits: Boolean,
//                         private val symbols: Boolean,
//                         private val minLengthError: String,
//                         private val maxLengthError: String,
//                         private val lowerCaseError: String,
//                         private val upperCaseError: String,
//                         private val digitsError: String,
//                         private val symbolError: String) : Validation {
//
//    override fun validate(field: String?) = ValidationResult(
//            when {
//                field.isNullOrEmpty() -> minLengthError
//                field.length < minLength -> minLengthError
//                field.length > maxLength -> maxLengthError
//                lowerCase && containsLowerCase(field).not() -> lowerCaseError
//                upperCase && containsUpperCase(field).not() -> upperCaseError
//                digits && containsDigits(field).not() -> digitsError
//                symbols && containsSymbol(field).not() -> symbolError
//                else -> ""
//            }
//    )
//
//    class Builder(context: Context) : BaseBuilder(context) {
//
//        private var minLength = getInteger(R.integer.min_field_length)
//        private var maxLength = getInteger(R.integer.max_field_length)
//        private var lowerCase = true
//        private var upperCase = true
//        private var digits = true
//        private var symbol = true
//
//        private var minLengthError = getString(R.string.password_should_be_longer)
//        private var maxLengthError = getString(R.string.password_should_be_smaller)
//        private var lowerCaseError = getString(R.string.password_must_contains_lowercase)
//        private var upperCaseError = getString(R.string.password_must_contains_uppercase)
//        private var digitsError = getString(R.string.password_must_contains_digits)
//        private var symbolError = getString(R.string.password_must_contains_symbols)
//
//        fun minLength(length: Int) = apply { if (length in 1..maxLength) minLength = length }
//        fun maxLength(length: Int) = apply { if (length >= minLength) maxLength = length }
//        fun lowerCase(flag: Boolean) = apply { lowerCase = flag }
//        fun upperCase(flag: Boolean) = apply { upperCase = flag }
//        fun digits(flag: Boolean) = apply { digits = flag }
//        fun symbol(flag: Boolean) = apply { symbol = flag }
//
//        fun minLengthError(error: String?) = apply { minLengthError = error.orEmpty() }
//        fun minLengthError(@StringRes id: Int) = apply { minLengthError = getString(id) }
//
//        fun maxLengthError(error: String?) = apply { maxLengthError = error.orEmpty() }
//        fun maxLengthError(@StringRes id: Int) = apply { maxLengthError = getString(id) }
//
//        fun lowerCaseError(error: String?) = apply { lowerCaseError = error.orEmpty() }
//        fun lowerCaseError(@StringRes id: Int) = apply { lowerCaseError = getString(id) }
//
//        fun upperCaseError(error: String?) = apply { upperCaseError = error.orEmpty() }
//        fun upperCaseError(@StringRes id: Int) = apply { upperCaseError = getString(id) }
//
//        fun digitsError(error: String?) = apply { digitsError = error.orEmpty() }
//        fun digitsError(@StringRes id: Int) = apply { digitsError = getString(id) }
//
//        fun symbolError(error: String?) = apply { symbolError = error.orEmpty() }
//        fun symbolError(@StringRes id: Int) = apply { symbolError = getString(id) }
//
//        fun build() = PasswordValidation(minLength, maxLength, lowerCase,
//                upperCase, digits, symbol, minLengthError, maxLengthError, lowerCaseError,
//                upperCaseError, digitsError, symbolError)
//    }
//}