package com.yarovoiag.library

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.yarovoiag.library.validations.*
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RegexValidationTest : BaseValidationTest() {

    private lateinit var lowerCaseText: String
    private lateinit var upperCaseText: String
    private lateinit var digitsText: String
    private lateinit var symbolsText: String
    private val digitsTextId = R.string.tests_digits
    private val lowerCaseTextId = R.string.tests_lower
    private val upperCaseTextId = R.string.tests_upper
    private val symbolsTextId = R.string.tests_symbols


    @Before
    override fun init() {
        super.init()
        lowerCaseText = getString(lowerCaseTextId)
        upperCaseText = getString(upperCaseTextId)
        digitsText = getString(digitsTextId)
        symbolsText = getString(symbolsTextId)
    }

    @Test
    fun defaultValidationTest() {
        val validation = RegexValidation.Builder(context).build()

        Assert.assertFalse(validation.validate(nullText).isValid)
        Assert.assertTrue(validation.validate(emptyText).isValid)
    }

    @Test
    fun digitsValidationTest() {
        val validation = RegexValidation.Builder(context)
                .regex(DIGITS_REGEX).build()

        Assert.assertFalse(validation.validate(nullText).isValid)
        Assert.assertFalse(validation.validate(emptyText).isValid)
        Assert.assertFalse(validation.validate(someText).isValid)
        Assert.assertTrue(validation.validate(digitsText).isValid)
    }

    @Test
    fun digitsValidationStringsTest() {
        val validation = RegexValidation.Builder(context)
                .regex(DIGITS_REGEX)
                .error(someErrorText)
                .build()

        Assert.assertEquals(validation.validate(someText).error, someErrorText)
        Assert.assertEquals(validation.validate(digitsText).error, emptyText)
    }

    @Test
    fun digitsValidationStringIdsTest() {
        val validation = RegexValidation.Builder(context)
                .regex(DIGITS_REGEX)
                .error(someErrorTextId)
                .build()

        Assert.assertEquals(validation.validate(someText).error, someErrorText)
        Assert.assertEquals(validation.validate(digitsText).error, emptyText)
    }


    @Test
    fun lowercaseValidationTest() {
        val validation = RegexValidation.Builder(context)
                .regex(LOWERCASE_REGEX).build()

        Assert.assertFalse(validation.validate(nullText).isValid)
        Assert.assertFalse(validation.validate(emptyText).isValid)
        Assert.assertFalse(validation.validate(upperCaseText).isValid)
        Assert.assertTrue(validation.validate(lowerCaseText).isValid)
    }

    @Test
    fun uppercaseValidationTest() {
        val validation = RegexValidation.Builder(context)
                .regex(UPPERCASE_REGEX).build()

        Assert.assertFalse(validation.validate(nullText).isValid)
        Assert.assertFalse(validation.validate(emptyText).isValid)
        Assert.assertFalse(validation.validate(lowerCaseText).isValid)
        Assert.assertTrue(validation.validate(upperCaseText).isValid)
    }

    @Test
    fun symbolsValidationTest() {
        val validation = RegexValidation.Builder(context)
                .regex(SYMBOLS_REGEX).build()

        Assert.assertFalse(validation.validate(nullText).isValid)
        Assert.assertFalse(validation.validate(emptyText).isValid)
        Assert.assertFalse(validation.validate(someText).isValid)
        Assert.assertTrue(validation.validate(symbolsText).isValid)
    }


    @After
    fun destroy() {
    }

}