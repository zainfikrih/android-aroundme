package men.ngopi.zain.aroundme.data.repository

import io.reactivex.Observable
import men.ngopi.zain.aroundme.data.model.PointLocation

interface RepositoryImpl {
    fun getPoints(location: PointLocation): Observable<List<PointLocation>>
}
