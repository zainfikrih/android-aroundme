package men.ngopi.zain.datapenduduk.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import men.ngopi.zain.datapenduduk.App
import men.ngopi.zain.datapenduduk.di.AppComponent

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observerChange()
    }

    fun getAppInjector(): AppComponent {
        return (applicationContext as App).appComponent
    }

    abstract fun observerChange()
}