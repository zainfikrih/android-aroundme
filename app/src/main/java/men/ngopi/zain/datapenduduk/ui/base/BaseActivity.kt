package men.ngopi.zain.datapenduduk.ui.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import men.ngopi.zain.datapenduduk.App
import men.ngopi.zain.datapenduduk.data.repository.Repository
import men.ngopi.zain.datapenduduk.di.AppComponent
import javax.inject.Inject

abstract class BaseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun getAppInjector(): AppComponent{
        return (applicationContext as App).appComponent
    }
}