package men.ngopi.zain.datapenduduk.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Observable
import men.ngopi.zain.datapenduduk.data.model.Person

@Dao
interface PersonDao {
    @Query("SELECT * FROM Person")
    fun getAll(): Observable<List<Person>>

    @Insert
    fun insert(person: Person)
}
