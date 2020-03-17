package men.ngopi.zain.datapenduduk.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import men.ngopi.zain.datapenduduk.data.repository.Repository
import men.ngopi.zain.datapenduduk.databinding.ActivityHomeBinding
import men.ngopi.zain.datapenduduk.ui.base.BaseActivity
import javax.inject.Inject

class HomeActivity : BaseActivity() {

    @Inject
    lateinit var repository: Repository

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getAppInjector().inject(this)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
