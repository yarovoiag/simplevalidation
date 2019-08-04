package com.yarovoiag.simplevalidation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val nicknameErrorTW by lazy { ErrorCleanerTextWatcher(tilNickName) }
    private val emailErrorTW by lazy { ErrorCleanerTextWatcher(tilEmail) }
    private val passwordErrorTW by lazy { ErrorCleanerTextWatcher(tilPassword) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etNickName.addTextChangedListener(nicknameErrorTW)
        etEmail.addTextChangedListener(emailErrorTW)
        etPassword.addTextChangedListener(passwordErrorTW)

        bValidate.setOnClickListener {
            validate()
        }
    }

    private fun validate() {
        val nickname = etNickName.text.toString()
        ValidationProvider.nicknameValidation.validate(nickname).takeUnless { it.isValid }
                ?.let {
                    tilNickName.error = it.error
                }

        val email = etEmail.text.toString()
        ValidationProvider.emailValidation.validate(email).takeUnless { it.isValid }
                ?.let {
                    tilEmail.error = it.error
                }

        val password = etPassword.text.toString()
        ValidationProvider.passwordValidation.validate(password).takeUnless { it.isValid }
                ?.let {
                    tilPassword.error = it.error
                }

    }

    override fun onDestroy() {
        etNickName.removeTextChangedListener(nicknameErrorTW)
        etEmail.removeTextChangedListener(emailErrorTW)
        etPassword.removeTextChangedListener(passwordErrorTW)
        super.onDestroy()
    }
}
