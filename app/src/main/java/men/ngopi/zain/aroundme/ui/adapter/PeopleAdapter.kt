package men.ngopi.zain.aroundme.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import men.ngopi.zain.aroundme.data.model.PointLocation
import men.ngopi.zain.aroundme.databinding.ItemPointBinding

class PointAdapter(private val pointLocations: List<PointLocation>) :
    RecyclerView.Adapter<PointAdapter.PointViewHolder>() {

    class PointViewHolder(private val binding: ItemPointBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(pointLocation: PointLocation) {
            binding.tvName.text = pointLocation.name
            val latLong = "Lat ${pointLocation.lat}, Long ${pointLocation.long}"
            binding.tvLatLong.text = latLong
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PointViewHolder {
        val binding = ItemPointBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return PointViewHolder(binding)
    }

    override fun getItemCount() = pointLocations.size

    override fun onBindViewHolder(holder: PointViewHolder, position: Int) {
        holder.bind(pointLocations[position])
    }
}