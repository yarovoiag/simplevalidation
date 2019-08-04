package com.yarovoiag.simplevalidation

import android.content.Context
import com.yarovoiag.library.ValidationSet
import com.yarovoiag.library.validations.EmailValidation
import com.yarovoiag.library.validations.EmptyValidation
import com.yarovoiag.library.validations.LengthValidation
import com.yarovoiag.library.validations.PasswordValidation

object ValidationProvider {

    val emailValidation by lazy { provideEmailValidation(App.instance) }
    // you can create your password validation using ValidationSet...
    val passwordValidation by lazy { providePasswordValidation(App.instance) }
    val nicknameValidation by lazy { provideNickNameValidation(App.instance) }

    private fun provideNickNameValidation(context: Context): ValidationSet {
        return ValidationSet(
                EmptyValidation.Builder(context)
                        .error(R.string.empty_error)
                        .build(),
                LengthValidation.Builder(context)
                        .minLength(4)
                        .maxLength(12)
                        .minLengthError(R.string.text_too_short_error)
                        .maxLengthError(R.string.text_too_long_error)
                        .build()
        )
    }

    private fun provideEmailValidation(context: Context) =
            EmailValidation.Builder(context)
                    .error(R.string.email_error)
                    .build()

    private fun providePasswordValidation(context: Context) =
            PasswordValidation.Builder(context)
                    .digits(false)
                    .symbol(false)
                    .lowerCase(true)
                    .upperCase(true)
                    .minLength(context.resources.getInteger(R.integer.min_password_length))
                    .maxLength(context.resources.getInteger(R.integer.max_password_length))
                    .minLengthError(R.string.password_too_short_error)
                    .maxLengthError(R.string.password_too_long_error)
                    .lowerCaseError(R.string.password_lowercase_error)
                    .upperCaseError(R.string.password_uppercase_error)
                    .build()


}