package men.ngopi.zain.datapenduduk.data.source.local.converter

import androidx.room.TypeConverter
import men.ngopi.zain.datapenduduk.data.model.Residence

object ResidenceConverter {
    @TypeConverter
    @JvmStatic
    fun fromResidence(value: Residence): Int {
        return value.ordinal
    }

    @TypeConverter
    @JvmStatic
    fun toResidence(value: Int): Residence {
        return when (value) {
            0 -> Residence.RUMAH
            1 -> Residence.KANTOR
            else -> throw IllegalArgumentException("Could not recognize residence")
        }
    }
}
