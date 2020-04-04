package men.ngopi.zain.aroundme

import android.app.Application
import men.ngopi.zain.aroundme.di.repositoryModule
import men.ngopi.zain.aroundme.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(viewModelModule, repositoryModule)
        }
    }
}
