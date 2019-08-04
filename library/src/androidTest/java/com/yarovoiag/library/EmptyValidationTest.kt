package com.yarovoiag.library

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.yarovoiag.library.validations.EmptyValidation
import org.junit.After
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EmptyValidationTest : BaseValidationTest() {

    @Test
    fun defaultValidationTest() {
        val validation = EmptyValidation.Builder(context).build()

        Assert.assertFalse(validation.validate(nullText).isValid)
        Assert.assertFalse(validation.validate(emptyText).isValid)
        Assert.assertTrue(validation.validate(someText).isValid)
    }

    @Test
    fun customValidationStringsTest() {
        val validation = EmptyValidation.Builder(context)
                .error(someErrorText)
                .build()

        Assert.assertEquals(validation.validate(nullText).error, someErrorText)
        Assert.assertEquals(validation.validate(emptyText).error, someErrorText)
        Assert.assertEquals(validation.validate(someText).error, emptyText)
    }

    @Test
    fun customValidationStringIdsTest() {
        val validation = EmptyValidation.Builder(context)
                .error(someErrorTextId)
                .build()

        Assert.assertEquals(validation.validate(nullText).error, someErrorText)
        Assert.assertEquals(validation.validate(emptyText).error, someErrorText)
        Assert.assertEquals(validation.validate(someText).error, emptyText)
    }

    @After
    fun destroy() {
    }

}