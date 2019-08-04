package com.yarovoiag.library

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.yarovoiag.library.validations.LowerCaseValidation
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LowerCaseValidationTest : BaseValidationTest() {

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
        val validation = LowerCaseValidation.Builder(context).build()

        Assert.assertFalse(validation.validate(nullText).isValid)
        Assert.assertFalse(validation.validate(emptyText).isValid)
        Assert.assertFalse(validation.validate(upperCaseText).isValid)
        Assert.assertTrue(validation.validate(lowerCaseText).isValid)
    }

    @Test
    fun customValidationStringsTest() {
        val validation = LowerCaseValidation.Builder(context)
                .error(someErrorText)
                .build()

        Assert.assertEquals(validation.validate(upperCaseText).error, someErrorText)
        Assert.assertEquals(validation.validate(lowerCaseText).error, emptyText)
    }

    @Test
    fun customValidationStringIdsTest() {
        val validation = LowerCaseValidation.Builder(context)
                .error(someErrorTextId)
                .build()

        Assert.assertEquals(validation.validate(upperCaseText).error, someErrorText)
        Assert.assertEquals(validation.validate(lowerCaseText).error, emptyText)
    }

}