package men.ngopi.zain.aroundme.ui.home

import android.os.Bundle
import android.widget.Toast
import men.ngopi.zain.aroundme.data.model.Location
import men.ngopi.zain.aroundme.databinding.ActivityHomeBinding
import men.ngopi.zain.aroundme.ui.base.BaseActivity
import men.ngopi.zain.aroundme.util.ext.observe
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : BaseActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val viewModel by viewModel<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getPoints(Location(100, 200))
    }

    override fun observerChange() {
        observe(viewModel.points, ::onPointsLoaded)
        observe(viewModel.message, ::onError)
    }

    private fun onPointsLoaded(points: List<Location>) {
        Toast.makeText(this, points[0].name + " " + points[0].lat, Toast.LENGTH_SHORT).show()
    }

    private fun onError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
