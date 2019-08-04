package com.yarovoiag.library

data class ValidationSetResult(val isValid: Boolean, val results: List<ValidationResult>) {

    val error: String?
        get() = getFirstError()?.error

    fun getErrors() = results.filterNot { it.isValid }

    fun getFirstError() = getErrors().firstOrNull()

}