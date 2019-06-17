package it.chutien.allwaylocaldb

import android.app.Application
import androidx.multidex.MultiDexApplication
import com.facebook.stetho.Stetho
import com.mooveit.library.Fakeit
import it.chutien.allwaylocaldb.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import java.util.*

/**
 * Created by ChuTien on ${1/25/2017}.
 */
class App : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(viewModelModule)
        }
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
        Fakeit.init()
    }
}