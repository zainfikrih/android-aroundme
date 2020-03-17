package men.ngopi.zain.datapenduduk.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "Person")
data class Person(
    @PrimaryKey
    val idCardNumber: String,
    val fullName: String,
    val accountNumber: String,
    @TypeConverters(Education::class)
    val levelOfEducation: Education,
    val dateOfBirth: String,
    @Embedded
    val address: Address
)

enum class Education(val value: Int) {
    SD(0),
    SMP(1),
    SMA(2),
    S1(3),
    S2(4),
    S3(5)
}