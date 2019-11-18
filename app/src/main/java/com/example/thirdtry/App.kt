package com.example.thirdtry

import android.app.Application


class App : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {

        var instance: App? = null
            private set
    }

}