package men.ngopi.zain.datapenduduk.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import men.ngopi.zain.datapenduduk.data.model.Person
import men.ngopi.zain.datapenduduk.data.source.local.converter.EducationConverter
import men.ngopi.zain.datapenduduk.data.source.local.converter.ResidenceConverter
import men.ngopi.zain.datapenduduk.util.Constant.DB_VERSION

@Database(entities = [Person::class], version = DB_VERSION, exportSchema = false)
@TypeConverters(EducationConverter::class, ResidenceConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun personDao(): PersonDao
}
