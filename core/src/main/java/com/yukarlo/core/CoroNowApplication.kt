package com.yukarlo.core

import android.app.Application

class CoroNowApplication(): Application() {

    override fun onCreate() {
        super.onCreate()

//        CoreComponentFactory.coreComponent(context = this)
//            .inject(tmdbApplication = this)
    }
}