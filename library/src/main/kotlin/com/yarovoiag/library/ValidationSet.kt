package com.yarovoiag.library

open class ValidationSet(private vararg val validation: Validation) : Validation {

    override fun validate(field: String?): ValidationResult {
        return ValidationResult(validation.map { it.validate(field) }
            .find { !it.isValid }?.error ?: "")
    }

    fun validateAll(field: String?): ValidationSetResult {
        val results = validation.map { it.validate(field) }
        val isValid = results.all { it.isValid }
        return ValidationSetResult(isValid, results)
    }

}