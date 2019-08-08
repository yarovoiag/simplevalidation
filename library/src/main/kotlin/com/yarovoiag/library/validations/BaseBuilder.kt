package com.yarovoiag.library.validations

import android.content.Context
import androidx.annotation.IntegerRes
import androidx.annotation.StringRes
import java.lang.ref.WeakReference

abstract class BaseBuilder(context: Context) {
    private val ctxWR = WeakReference(context)

    protected fun getInteger(@IntegerRes id: Int) = ctxWR.get()?.resources?.getInteger(id) ?: 0

    protected fun getString(@StringRes id: Int) = ctxWR.get()?.resources?.getString(id) ?: ""
}