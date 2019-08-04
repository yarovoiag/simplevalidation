package com.yarovoiag.library.validations

val SYMBOLS_REGEX = "[\\W_]+".toRegex()
val DIGITS_REGEX = "\\d+".toRegex()
val LOWERCASE_REGEX = "[a-z]+".toRegex()
val UPPERCASE_REGEX = "[A-Z]+".toRegex()
val ANY_REGEX = ".*".toRegex()

fun containsUpperCase(field: String?) = field?.let { UPPERCASE_REGEX.containsMatchIn(it) } == true
fun containsLowerCase(field: String?) = field?.let { LOWERCASE_REGEX.containsMatchIn(it) } == true
fun containsDigits(field: String?) = field?.let { DIGITS_REGEX.containsMatchIn(it) } == true
fun containsSymbol(field: String?) = field?.let { SYMBOLS_REGEX.containsMatchIn(it) } == true