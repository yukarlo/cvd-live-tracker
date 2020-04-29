package com.yukarlo.core.di

import android.content.Context

object CoreComponentFactory {

    private var mCoreComponent: CoreComponent? = null

    fun coreComponent(context: Context): CoreComponent {
        val component = mCoreComponent ?: DaggerCoreComponent.factory()
            .create(application = context.applicationContext)
        mCoreComponent = component
        return component
    }
}
