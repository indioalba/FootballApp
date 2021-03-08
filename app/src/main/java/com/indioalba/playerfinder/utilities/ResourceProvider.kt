package com.indioalba.playerfinder.utilities

import android.content.Context
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ResourceProvider @Inject constructor(@param:ApplicationContext private val context: Context) {

    fun getString(@StringRes resourceIdRes: Int): String = context.getString(resourceIdRes)

    fun getString(@StringRes resourceIdRes: Int, @StringRes resourceIdResArgument: Int): String =
        context.getString(resourceIdRes, resourceIdResArgument)
}