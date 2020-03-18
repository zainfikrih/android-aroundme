package men.ngopi.zain.datapenduduk.data.source.local.converter

import androidx.room.TypeConverter
import men.ngopi.zain.datapenduduk.data.model.Education

object EducationConverter {
    @TypeConverter
    @JvmStatic
    fun fromEducation(education: Education): Int {
        return education.ordinal
    }

    @TypeConverter
    @JvmStatic
    fun toEducation(value: Int): Education {
        return when (value) {
            0 -> Education.SD
            1 -> Education.SMP
            2 -> Education.SMA
            3 -> Education.S1
            4 -> Education.S2
            5 -> Education.S3
            else -> throw IllegalArgumentException("Could not recognize education")
        }
    }
}
