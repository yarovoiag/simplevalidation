package com.yarovoiag.library

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.yarovoiag.library.validations.LengthValidation
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LengthValidationTest : BaseValidationTest() {

    @Test
    fun defaultValidationTest() {
        val validation = LengthValidation.Builder(context).build()

        Assert.assertFalse(validation.validate(nullText).isValid)
        Assert.assertFalse(validation.validate(emptyText).isValid)
        Assert.assertFalse(validation.validate(someLongText).isValid)
        Assert.assertTrue(validation.validate(someText).isValid)
    }

    @Test
    fun customValidationStringsTest() {
        val validation = LengthValidation.Builder(context)
                .minLength(someText.length + 1)
                .maxLength(someLongText.length - 1)
                .minLengthError(someErrorText)
                .maxLengthError(someErrorTextSecond)
                .build()

        Assert.assertEquals(validation.validate(someText).error, someErrorText)
        Assert.assertEquals(validation.validate(someLongText).error, someErrorTextSecond)
    }

    @Test
    fun customValidationStringIdsTest() {
        val validation = LengthValidation.Builder(context)
                .minLength(someText.length + 1)
                .maxLength(someLongText.length - 1)
                .minLengthError(someErrorTextId)
                .maxLengthError(someErrorSecondTextId)
                .build()

        Assert.assertEquals(validation.validate(someText).error, someErrorText)
        Assert.assertEquals(validation.validate(someLongText).error, someErrorTextSecond)
    }

}