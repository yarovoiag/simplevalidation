package com.yarovoiag.library

data class ValidationResult(val error: String = "", val isValid: Boolean = error.isEmpty())