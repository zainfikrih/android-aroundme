package men.ngopi.zain.datapenduduk.data.repository

import io.reactivex.Observable
import men.ngopi.zain.datapenduduk.data.model.Person
import men.ngopi.zain.datapenduduk.data.model.Province

interface DataSource {
    fun getAllProvince(): Observable<List<Province>>

    fun getPeople(): Observable<List<Person>>

    fun insertPerson(person: Person)

    fun getCacheAllProvince(): List<Province>?

    fun setCacheAllProvince(provinces: List<Province>)
}
