package com.yarovoiag.library

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.yarovoiag.library.validations.EmailValidation
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EmailValidationTest : BaseValidationTest() {

    private lateinit var validEmails: List<String>
    private lateinit var invalidEmails: List<String>

    @Before
    override fun init() {
        super.init()
        validEmails = getStringArray(R.array.valid_emails).toList()
        invalidEmails = getStringArray(R.array.invalid_emails).toList()
    }

    @Test
    fun defaultValidationTest() {
        val validation = EmailValidation.Builder(context).build()
        validEmails.forEach {
            Assert.assertTrue(validation.validate(it).isValid)
        }

        invalidEmails.forEach {
            Assert.assertFalse(validation.validate(it).isValid)
        }
    }

    @Test
    fun customValidationStringsTest() {
        val validation = EmailValidation.Builder(context)
                .error(someErrorText)
                .build()

        Assert.assertEquals(validation.validate(nullText).error, someErrorText)
        Assert.assertEquals(validation.validate(emptyText).error, someErrorText)
        Assert.assertEquals(validation.validate(invalidEmails.first()).error, someErrorText)
        Assert.assertEquals(validation.validate(validEmails.first()).error, emptyText)
    }

    @Test
    fun customValidationStringIdsTest() {
        val validation = EmailValidation.Builder(context)
                .error(someErrorTextId)
                .build()

        Assert.assertEquals(validation.validate(nullText).error, someErrorText)
        Assert.assertEquals(validation.validate(emptyText).error, someErrorText)
        Assert.assertEquals(validation.validate(invalidEmails.first()).error, someErrorText)
        Assert.assertEquals(validation.validate(validEmails.first()).error, emptyText)
    }

    @After
    fun destroy() {
    }

}