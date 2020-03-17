package men.ngopi.zain.datapenduduk.data.source.local

import io.reactivex.Observable
import men.ngopi.zain.datapenduduk.data.model.Person
import men.ngopi.zain.datapenduduk.data.source.local.room.AppDatabase

class LocalRepository(appDatabase: AppDatabase) {
    private val personDao = appDatabase.personDao()

    fun getPeople(): Observable<List<Person>> {
        return personDao.getAll()
    }

    fun insertPerson(person: Person) {
        personDao.insert(person)
    }
}