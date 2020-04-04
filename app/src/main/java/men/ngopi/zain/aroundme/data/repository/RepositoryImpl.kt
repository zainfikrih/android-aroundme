package men.ngopi.zain.aroundme.data.repository

import io.reactivex.Observable
import men.ngopi.zain.aroundme.data.model.Location

interface RepositoryImpl {
    fun getPoints(location: Location): Observable<List<Location>>
}
