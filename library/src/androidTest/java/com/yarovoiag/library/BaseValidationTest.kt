package com.yarovoiag.library

import android.content.Context
import androidx.annotation.ArrayRes
import androidx.annotation.StringRes
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Before

abstract class BaseValidationTest {
    protected val emptyText = ""
    protected val nullText = null
    protected val someTextId = R.string.tests_some_text
    protected val someLongTextId = R.string.tests_some_long_text
    protected val someErrorTextId = R.string.tests_some_error
    protected val someErrorSecondTextId = R.string.tests_some_error_second

    protected lateinit var context: Context
    protected lateinit var someText: String
    protected lateinit var someLongText: String
    protected lateinit var someErrorText: String
    protected lateinit var someErrorTextSecond: String

    @Before
    open fun init() {
        context = InstrumentationRegistry.getInstrumentation().context
        someText = context.getString(someTextId)
        someLongText = context.getString(someLongTextId)
        someErrorText = context.getString(someErrorTextId)
        someErrorTextSecond = context.getString(someErrorSecondTextId)

    }

    protected fun getString(@StringRes resId: Int) = context.getString(resId)
    protected fun getStringArray(@ArrayRes resId: Int) = context.resources.getStringArray(resId)

}