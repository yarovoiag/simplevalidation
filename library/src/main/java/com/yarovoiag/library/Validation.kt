package com.yarovoiag.library

interface Validation {

    /**
     * Validate field and return result.
     * @param field validated field.
     * @return See [ValidationResult]
     */
    fun validate(field: String?): ValidationResult

}