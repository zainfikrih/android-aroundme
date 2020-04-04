package men.ngopi.zain.aroundme.ui.home

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import men.ngopi.zain.aroundme.R
import men.ngopi.zain.aroundme.data.model.PointLocation
import men.ngopi.zain.aroundme.databinding.ActivityHomeBinding
import men.ngopi.zain.aroundme.ui.adapter.PointAdapter
import men.ngopi.zain.aroundme.ui.base.BaseActivity
import men.ngopi.zain.aroundme.util.ext.invisible
import men.ngopi.zain.aroundme.util.ext.observe
import men.ngopi.zain.aroundme.util.ext.visible
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeActivity : BaseActivity() {

    private val requestCodeLocation: Int = 101
    private lateinit var binding: ActivityHomeBinding
    private val viewModel by viewModel<HomeViewModel>()
    private var googleMap: GoogleMap? = null
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var markerList: MutableList<Marker> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(onMapReadyCallback)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
    }

    override fun observerChange() {
        observe(viewModel.points, ::onPointsLoaded)
        observe(viewModel.message, ::onError)
    }

    private fun onPointsLoaded(points: List<PointLocation>) {
        binding.progressBar.invisible()
        binding.rvPoint.visible()
        points.forEach {
            drawPoint(it)
        }
        binding.rvPoint.apply {
            layoutManager = LinearLayoutManager(
                this@HomeActivity, RecyclerView.VERTICAL, false
            ).apply {
                reverseLayout = false
                stackFromEnd = false
            }
            setHasFixedSize(true)
            itemAnimator = DefaultItemAnimator()
            PointAdapter(points).run {
                adapter = this
                onItemClick = { pos, _ ->
                    val latLng = LatLng(points[pos].lat, points[pos].long)
                    googleMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
                    markerList[pos].showInfoWindow()
                }
            }
        }
    }

    private fun onError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private val onMapReadyCallback = OnMapReadyCallback { googleMap ->
        this.googleMap = googleMap
        googleMap.setOnMapClickListener(onMapClicked)
        requestPermission()
    }

    private val onMapClicked = GoogleMap.OnMapClickListener {
        drawMyLocation(latLng = it)
    }

    @SuppressLint("MissingPermission")
    private fun currentLocation() {
        fusedLocationClient.lastLocation
            .addOnSuccessListener {
                val latLng = LatLng(
                    it.latitude,
                    it.longitude
                )
                drawMyLocation(latLng)
            }
    }

    private fun drawPoint(pointLocation: PointLocation) {
        val latLng = LatLng(pointLocation.lat, pointLocation.long)
        val marker = googleMap?.addMarker(
            MarkerOptions()
                .position(latLng)
                .icon(bitmapDescriptorFromVector())
                .title(pointLocation.name)
        )
        marker?.run {
            markerList.add(this)
        }
    }

    private fun drawMyLocation(latLng: LatLng) {
        binding.progressBar.visible()
        googleMap?.apply {
            clear()
            addMarker(
                MarkerOptions()
                    .position(latLng)
                    .title("Your Location")
            )
            moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
        }.also {
            binding.rvPoint.invisible()
            viewModel.getPoints(PointLocation(latLng.latitude, latLng.longitude))
        }
    }

    private fun requestPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED
        ) {

            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                requestCodeLocation
            )
        } else {
            currentLocation()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            requestCodeLocation -> {
                currentLocation()
            }
        }
    }

    private fun bitmapDescriptorFromVector(): BitmapDescriptor? {
        return ContextCompat.getDrawable(
            this,
            R.drawable.ic_simple_icon_location
        )?.run {
            setBounds(0, 0, intrinsicWidth, intrinsicHeight)
            val bitmap =
                Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888)
            draw(Canvas(bitmap))
            BitmapDescriptorFactory.fromBitmap(bitmap)
        }
    }
}
