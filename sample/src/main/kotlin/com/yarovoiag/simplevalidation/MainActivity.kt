package com.yarovoiag.simplevalidation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yarovoiag.library.ValidationWatcher
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val nicknameErrorTW by lazy { ErrorCleanerTextWatcher(tilNickName) }
    private val emailErrorTW by lazy { ErrorCleanerTextWatcher(tilEmail) }

    // using Watcher for validation
    private val passwordValidationTW by lazy {
        ValidationWatcher(tilPassword, ValidationFactory.password(this@MainActivity))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etNickName.addTextChangedListener(nicknameErrorTW)
        etEmail.addTextChangedListener(emailErrorTW)
        etPassword.addTextChangedListener(passwordValidationTW)

        bValidate.setOnClickListener {
            validate()
        }
    }

    private fun validate() {
        val nickname = etNickName.text.toString()
        ValidationFactory.nickname(this).validate(nickname).takeUnless { it.isValid }
            ?.let {
                tilNickName.error = it.error
            }

        val email = etEmail.text.toString()
        ValidationFactory.email(this).validate(email).takeUnless { it.isValid }
            ?.let {
                tilEmail.error = it.error
            }

        val password = etPassword.text.toString()
        // manual start validation
        passwordValidationTW.validate(password)

    }

    override fun onDestroy() {
        etNickName.removeTextChangedListener(nicknameErrorTW)
        etEmail.removeTextChangedListener(emailErrorTW)
        etPassword.removeTextChangedListener(passwordValidationTW)
        super.onDestroy()
    }
}
