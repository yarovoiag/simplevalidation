package com.yarovoiag.library

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.yarovoiag.library.validations.*
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SymbolsValidationTest : BaseValidationTest() {
    private lateinit var symbolsText: String
    private lateinit var lowerCaseText: String
    private val lowerCaseTextId = R.string.tests_lower
    private val symbolsTextId = R.string.tests_symbols


    @Before
    override fun init() {
        super.init()
        symbolsText = getString(symbolsTextId)
        lowerCaseText = getString(lowerCaseTextId)
    }

    @Test
    fun defaultValidationTest() {
        val validation = SymbolsValidation.Builder(context).build()

        Assert.assertFalse(validation.validate(nullText).isValid)
        Assert.assertFalse(validation.validate(emptyText).isValid)
        Assert.assertFalse(validation.validate(lowerCaseText).isValid)
        Assert.assertTrue(validation.validate(symbolsText).isValid)
    }

    @Test
    fun customValidationStringsTest() {
        val validation = SymbolsValidation.Builder(context)
                .error(someErrorText)
                .build()

        Assert.assertEquals(validation.validate(lowerCaseText).error, someErrorText)
        Assert.assertEquals(validation.validate(symbolsText).error, emptyText)
    }

    @Test
    fun customValidationStringIdsTest() {
        val validation = SymbolsValidation.Builder(context)
                .error(someErrorTextId)
                .build()

        Assert.assertEquals(validation.validate(lowerCaseText).error, someErrorText)
        Assert.assertEquals(validation.validate(symbolsText).error, emptyText)
    }


    @After
    fun destroy() {
    }

}