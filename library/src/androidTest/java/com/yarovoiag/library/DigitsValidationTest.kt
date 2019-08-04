package com.yarovoiag.library

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.yarovoiag.library.validations.DigitsValidation
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DigitsValidationTest : BaseValidationTest() {

    private lateinit var digitsText: String
    private val digitsTextId = R.string.tests_digits

    @Before
    override fun init() {
        super.init()
        digitsText = getString(digitsTextId)
    }

    @Test
    fun defaultValidationTest() {
        val validation = DigitsValidation.Builder(context).build()

        Assert.assertFalse(validation.validate(nullText).isValid)
        Assert.assertFalse(validation.validate(emptyText).isValid)
        Assert.assertFalse(validation.validate(someText).isValid)
        Assert.assertTrue(validation.validate(digitsText).isValid)
    }

    @Test
    fun customValidationStringsTest() {
        val validation = DigitsValidation.Builder(context)
                .error(someErrorText)
                .build()

        Assert.assertEquals(validation.validate(nullText).error, someErrorText)
        Assert.assertEquals(validation.validate(emptyText).error, someErrorText)
        Assert.assertEquals(validation.validate(someText).error, someErrorText)
        Assert.assertEquals(validation.validate(digitsText).error, emptyText)
    }

    @Test
    fun customValidationStringIdsTest() {
        val validation = DigitsValidation.Builder(context)
                .error(someErrorTextId)
                .build()

        Assert.assertEquals(validation.validate(nullText).error, someErrorText)
        Assert.assertEquals(validation.validate(emptyText).error, someErrorText)
        Assert.assertEquals(validation.validate(someText).error, someErrorText)
        Assert.assertEquals(validation.validate(digitsText).error, emptyText)
    }

    @After
    fun destroy() {
    }

}