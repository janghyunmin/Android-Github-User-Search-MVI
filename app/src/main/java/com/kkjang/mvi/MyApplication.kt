package com.kkjang.mvi

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import com.kkjang.mvi.BuildConfig

@HiltAndroidApp
class MyApplication : Application() {
    private val notLoggingTree = object : Timber.Tree() {
        override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        }
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(if (BuildConfig.DEBUG) Timber.DebugTree() else notLoggingTree)
    }
}