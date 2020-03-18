package men.ngopi.zain.datapenduduk

import android.app.Application
import android.util.Log
import com.orhanobut.hawk.Hawk
import men.ngopi.zain.datapenduduk.di.AppComponent
import men.ngopi.zain.datapenduduk.di.DaggerAppComponent

class App : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        Hawk.init(applicationContext).setLogInterceptor { message ->
            if (BuildConfig.DEBUG) {
                Log.d("Hawk", message)
            }
        }.build()
        appComponent = DaggerAppComponent.builder().application(this).build()
    }
}
