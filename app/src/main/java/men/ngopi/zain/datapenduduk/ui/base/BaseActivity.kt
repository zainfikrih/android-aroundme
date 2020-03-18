package men.ngopi.zain.datapenduduk.ui.base

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import men.ngopi.zain.datapenduduk.App
import men.ngopi.zain.datapenduduk.di.AppComponent

abstract class BaseActivity : AppCompatActivity() {

    protected val compositeDisposable by lazy { CompositeDisposable() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observerChange()
    }

    fun getAppInjector(): AppComponent {
        return (applicationContext as App).appComponent
    }

    fun setupActionBar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = title
    }

    fun navigateTo(to: Any, intentExtras: Intent?, addToBackStack: Boolean? = true) {
        val intent = Intent(this, to::class.java)
        intentExtras?.let {
            intent.putExtras(it)
        }
        startActivity(intent)
        addToBackStack?.let {
            if (!it) finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onDestroy() {
        with(compositeDisposable) {
            clear()
            dispose()
        }
        super.onDestroy()
    }

    abstract fun observerChange()
}
