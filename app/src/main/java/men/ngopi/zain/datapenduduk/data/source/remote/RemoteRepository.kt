package men.ngopi.zain.datapenduduk.data.source.remote

import io.reactivex.Observable
import men.ngopi.zain.datapenduduk.data.model.Province
import men.ngopi.zain.datapenduduk.data.source.remote.retrofit.RetrofitServices

class RemoteRepository(private val services: RetrofitServices) {
    fun getAllProvince(): Observable<List<Province>> {
        return services.getAllProvince()
            .flatMap {
                Observable.just(it.allProvince)
            }
    }
}
