package com.example.myapplication

import android.app.Application
import android.content.Context

class App : Application() {
    var URL: String? = ""
    override fun onCreate() {
        super.onCreate()
        instance = this
        context = this.applicationContext
    }

    companion object {
        var instance: App? = null
            private set

        /**
         * 获取全局上下文
         */
        var context: Context? = null
            private set
    }
}