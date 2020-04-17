package com.yukarlo.core

import android.app.Application
import com.yukarlo.core.di.CoreComponentFactory

class CoroNowApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        CoreComponentFactory.coreComponent(context = this)
            .inject(coroNowApplication = this)
    }
}
