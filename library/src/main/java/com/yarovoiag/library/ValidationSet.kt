package com.yarovoiag.library

class ValidationSet(private vararg val validation: Validation) {

    fun validate(field: String?): ValidationSetResult {
        val results = validation.map { it.validate(field) }
        val isValid = results.all { it.isValid }
        return ValidationSetResult(isValid, results)
    }


}