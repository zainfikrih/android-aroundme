package men.ngopi.zain.aroundme.data.repository

import io.reactivex.Observable
import men.ngopi.zain.aroundme.data.model.PointLocation
import men.ngopi.zain.aroundme.data.source.MockRemoteRepository
import java.util.concurrent.TimeUnit

class Repository(
    private val remoteRepository: MockRemoteRepository
) : RepositoryImpl {
    override fun getPoints(location: PointLocation): Observable<List<PointLocation>> {
        return remoteRepository
            .getPoints(location)
            .delay(1, TimeUnit.SECONDS)
            .onErrorResumeNext { it: Throwable ->
                Observable.error(it)
            }
    }
}
