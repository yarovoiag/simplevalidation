package com.yarovoiag.library

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.yarovoiag.library.validations.PasswordValidation
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PasswordValidationTest : BaseValidationTest() {

    private lateinit var passwordText: String
    private lateinit var lowerCaseText: String
    private lateinit var upperCaseText: String
    private lateinit var digitsText: String
    private lateinit var symbolsText: String
    private val digitsTextId = R.string.tests_digits
    private val lowerCaseTextId = R.string.tests_lower
    private val upperCaseTextId = R.string.tests_upper
    private val passwordTextId = R.string.tests_password_full
    private val symbolsTextId = R.string.tests_symbols


    @Before
    override fun init() {
        super.init()
        passwordText = getString(passwordTextId)
        lowerCaseText = getString(lowerCaseTextId)
        upperCaseText = getString(upperCaseTextId)
        digitsText = getString(digitsTextId)
        symbolsText = getString(symbolsTextId)
    }

    @Test
    fun defaultValidationTest() {
        val validation = PasswordValidation.Builder(context).build()

        Assert.assertFalse(validation.validate(nullText).isValid)
        Assert.assertFalse(validation.validate(emptyText).isValid)
        Assert.assertFalse(validation.validate(someText).isValid)
        Assert.assertFalse(validation.validate(someLongText).isValid)
        Assert.assertFalse(validation.validate(lowerCaseText).isValid)
        Assert.assertFalse(validation.validate(upperCaseText).isValid)
        Assert.assertFalse(validation.validate(digitsText).isValid)
        Assert.assertFalse(validation.validate(symbolsText).isValid)
        Assert.assertTrue(validation.validate(passwordText).isValid)
    }

    @Test
    fun customValidationLengthStringsTest() {
        val validation = PasswordValidation.Builder(context)
                .digits(false)
                .symbol(false)
                .lowerCase(false)
                .upperCase(false)
                .minLength(someText.length + 1)
                .maxLength(someLongText.length - 1)
                .minLengthError(someErrorText)
                .maxLengthError(someErrorTextSecond)
                .build()

        Assert.assertEquals(validation.validate(someText).error, someErrorText)
        Assert.assertEquals(validation.validate(someLongText).error, someErrorTextSecond)
    }

    @Test
    fun customValidationLengthStringIdsTest() {
        val validation = PasswordValidation.Builder(context)
                .digits(false)
                .symbol(false)
                .lowerCase(false)
                .upperCase(false)
                .minLength(someText.length + 1)
                .maxLength(someLongText.length - 1)
                .minLengthError(someErrorTextId)
                .maxLengthError(someErrorSecondTextId)
                .build()

        Assert.assertEquals(validation.validate(someText).error, someErrorText)
        Assert.assertEquals(validation.validate(someLongText).error, someErrorTextSecond)
    }

    @Test
    fun customValidationLowerStringsTest() {
        val validation = PasswordValidation.Builder(context)
                .digits(false)
                .symbol(false)
                .lowerCase(true)
                .upperCase(false)
                .minLength(0)
                .maxLength(Int.MAX_VALUE)
                .lowerCaseError(someErrorText)
                .build()

        Assert.assertEquals(validation.validate(upperCaseText).error, someErrorText)
        Assert.assertEquals(validation.validate(lowerCaseText).error, emptyText)
    }

    @Test
    fun customValidationLowerStringIdsTest() {
        val validation = PasswordValidation.Builder(context)
                .digits(false)
                .symbol(false)
                .lowerCase(true)
                .upperCase(false)
                .minLength(0)
                .maxLength(Int.MAX_VALUE)
                .lowerCaseError(someErrorTextId)
                .build()

        Assert.assertEquals(validation.validate(upperCaseText).error, someErrorText)
        Assert.assertEquals(validation.validate(lowerCaseText).error, emptyText)
    }

    @Test
    fun customValidationUpperStringsTest() {
        val validation = PasswordValidation.Builder(context)
                .digits(false)
                .symbol(false)
                .lowerCase(false)
                .upperCase(true)
                .minLength(0)
                .maxLength(Int.MAX_VALUE)
                .upperCaseError(someErrorText)
                .build()

        Assert.assertEquals(validation.validate(lowerCaseText).error, someErrorText)
        Assert.assertEquals(validation.validate(upperCaseText).error, emptyText)
    }

    @Test
    fun customValidationUpperStringIdsTest() {
        val validation = PasswordValidation.Builder(context)
                .digits(false)
                .symbol(false)
                .lowerCase(false)
                .upperCase(true)
                .minLength(0)
                .maxLength(Int.MAX_VALUE)
                .upperCaseError(someErrorTextId)
                .build()

        Assert.assertEquals(validation.validate(lowerCaseText).error, someErrorText)
        Assert.assertEquals(validation.validate(upperCaseText).error, emptyText)
    }

    @Test
    fun customValidationDigitsStringsTest() {
        val validation = PasswordValidation.Builder(context)
                .digits(true)
                .symbol(false)
                .lowerCase(false)
                .upperCase(false)
                .minLength(0)
                .maxLength(Int.MAX_VALUE)
                .digitsError(someErrorText)
                .build()

        Assert.assertEquals(validation.validate(someText).error, someErrorText)
        Assert.assertEquals(validation.validate(digitsText).error, emptyText)
    }

    @Test
    fun customValidationDigitsStringIdsTest() {
        val validation = PasswordValidation.Builder(context)
                .digits(true)
                .symbol(false)
                .lowerCase(false)
                .upperCase(false)
                .minLength(0)
                .maxLength(Int.MAX_VALUE)
                .digitsError(someErrorTextId)
                .build()

        Assert.assertEquals(validation.validate(someText).error, someErrorText)
        Assert.assertEquals(validation.validate(digitsText).error, emptyText)
    }

    @Test
    fun customValidationSymbolsStringsTest() {
        val validation = PasswordValidation.Builder(context)
                .digits(false)
                .symbol(true)
                .lowerCase(false)
                .upperCase(false)
                .minLength(0)
                .maxLength(Int.MAX_VALUE)
                .symbolError(someErrorText)
                .build()

        Assert.assertEquals(validation.validate(lowerCaseText).error, someErrorText)
        Assert.assertEquals(validation.validate(symbolsText).error, emptyText)
    }

    @Test
    fun customValidationSymbolsStringIdsTest() {
        val validation = PasswordValidation.Builder(context)
                .digits(false)
                .symbol(true)
                .lowerCase(false)
                .upperCase(false)
                .minLength(0)
                .maxLength(Int.MAX_VALUE)
                .symbolError(someErrorTextId)
                .build()

        Assert.assertEquals(validation.validate(lowerCaseText).error, someErrorText)
        Assert.assertEquals(validation.validate(symbolsText).error, emptyText)
    }

}