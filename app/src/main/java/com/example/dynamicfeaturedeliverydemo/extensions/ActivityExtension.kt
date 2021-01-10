package com.example.dynamicfeaturedeliverydemo.extensions

import android.app.Activity
import com.example.dynamicfeaturedeliverydemo.CoreComponentProvider

fun Activity.coreComponent() =
    (applicationContext as? CoreComponentProvider)?.provideAppComponent()
        ?: throw IllegalStateException("CoreComponentProvider not implemented: $applicationContext")