package men.ngopi.zain.datapenduduk.ui.home

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import men.ngopi.zain.datapenduduk.data.model.Province
import men.ngopi.zain.datapenduduk.databinding.ActivityHomeBinding
import men.ngopi.zain.datapenduduk.di.ViewModelFactory
import men.ngopi.zain.datapenduduk.ui.base.BaseActivity
import men.ngopi.zain.datapenduduk.util.ext.observe
import javax.inject.Inject

class HomeActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        getAppInjector().inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadAllProvince()
    }

    override fun observerChange() {
        observe(viewModel.allProvince, ::onAllProvinceLoaded)
        observe(viewModel.message, ::onError)
    }

    private fun onAllProvinceLoaded(provinces: List<Province>) {
        Toast.makeText(this, provinces[0].name, Toast.LENGTH_SHORT).show()
    }

    private fun onError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
