package men.ngopi.zain.datapenduduk

import android.app.Application
import men.ngopi.zain.datapenduduk.di.AppComponent
import men.ngopi.zain.datapenduduk.di.DaggerAppComponent

class App : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().application(this).build()
    }
}