package com.yukarlo.main

import android.app.Application
import com.yukarlo.main.di.CoreComponentFactory

class CoroNowApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        CoreComponentFactory.coreComponent(context = this)
            .inject(coroNowApplication = this)
    }
}
