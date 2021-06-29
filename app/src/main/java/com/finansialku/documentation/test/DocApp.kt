package com.finansialku.documentation.test

import androidx.multidex.MultiDexApplication
import com.finansialku.documentation.core.di.coreModules
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class DocApp : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        Logger.addLogAdapter(AndroidLogAdapter())
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@DocApp)
            modules(
                coreModules
            )
        }
    }
}
