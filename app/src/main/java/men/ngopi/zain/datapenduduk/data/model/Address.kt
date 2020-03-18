package men.ngopi.zain.datapenduduk.data.model

import androidx.room.Embedded
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import men.ngopi.zain.datapenduduk.data.source.local.converter.ResidenceConverter

data class Address(
    val legalAddress: String,
    @TypeConverters(ResidenceConverter::class)
    val typeOfResidence: Residence,
    val blockNumber: String,
    @Embedded
    val province: Province
)

data class Province(
    @SerializedName("id")
    val id: String,
    @SerializedName("nama")
    val name: String
)

enum class Residence(val value: Int) {
    RUMAH(0),
    KANTOR(1)
}
