package men.ngopi.zain.datapenduduk.data.repository

import io.reactivex.Observable
import io.reactivex.functions.Function
import men.ngopi.zain.datapenduduk.data.model.Person
import men.ngopi.zain.datapenduduk.data.model.Province
import men.ngopi.zain.datapenduduk.data.source.local.LocalRepository
import men.ngopi.zain.datapenduduk.data.source.pref.PrefRepository
import men.ngopi.zain.datapenduduk.data.source.remote.RemoteRepository

class Repository(
    private val prefRepository: PrefRepository,
    private val localRepository: LocalRepository,
    private val remoteRepository: RemoteRepository
) : DataSource {
    override fun getAllProvince(): Observable<List<Province>> {
        val cacheAllProvince = getCacheAllProvince()
        val cacheObservable = if (cacheAllProvince != null)
            Observable.just(cacheAllProvince)
        else
            Observable.empty()

        val remoteObservable = remoteRepository.getAllProvince()
            .flatMap {
                setCacheAllProvince(it)
                Observable.just(it)
            }
            .onErrorResumeNext(Function {
                return@Function if (cacheAllProvince != null)
                    Observable.just(cacheAllProvince)
                else
                    Observable.error(it)
            })
        return Observable.concatArrayEager(cacheObservable, remoteObservable)
    }

    override fun getPeople(): Observable<List<Person>> {
        return localRepository.getPeople()
    }

    override fun insertPerson(person: Person) {
        localRepository.insertPerson(person)
    }

    override fun getCacheAllProvince(): List<Province>? {
        return prefRepository.getAllProvince()
    }

    override fun setCacheAllProvince(provinces: List<Province>) {
        prefRepository.setAllProvince(provinces)
    }
}
