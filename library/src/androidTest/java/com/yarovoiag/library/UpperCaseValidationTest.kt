package com.yarovoiag.library

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.yarovoiag.library.validations.UpperCaseValidation
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UpperCaseValidationTest : BaseValidationTest() {

    private lateinit var lowerCaseText: String
    private lateinit var upperCaseText: String
    private val lowerCaseTextId = R.string.tests_lower
    private val upperCaseTextId = R.string.tests_upper

    @Before
    override fun init() {
        super.init()
        lowerCaseText = getString(lowerCaseTextId)
        upperCaseText = getString(upperCaseTextId)
    }

    @Test
    fun defaultValidationTest() {
        val validation = UpperCaseValidation.Builder(context).build()

        Assert.assertFalse(validation.validate(nullText).isValid)
        Assert.assertFalse(validation.validate(emptyText).isValid)
        Assert.assertFalse(validation.validate(lowerCaseText).isValid)
        Assert.assertTrue(validation.validate(upperCaseText).isValid)
    }

    @Test
    fun customValidationStringsTest() {
        val validation = UpperCaseValidation.Builder(context)
                .error(someErrorText)
                .build()

        Assert.assertEquals(validation.validate(lowerCaseText).error, someErrorText)
        Assert.assertEquals(validation.validate(upperCaseText).error, emptyText)
    }

    @Test
    fun customValidationStringIdsTest() {
        val validation = UpperCaseValidation.Builder(context)
                .error(someErrorTextId)
                .build()

        Assert.assertEquals(validation.validate(lowerCaseText).error, someErrorText)
        Assert.assertEquals(validation.validate(upperCaseText).error, emptyText)
    }

}