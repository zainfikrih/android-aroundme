package men.ngopi.zain.datapenduduk.ui.home

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import men.ngopi.zain.datapenduduk.databinding.ActivityHomeBinding
import men.ngopi.zain.datapenduduk.di.ViewModelFactory
import men.ngopi.zain.datapenduduk.ui.base.BaseActivity
import javax.inject.Inject

class HomeActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getAppInjector().inject(this)
        val viewModel = ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
