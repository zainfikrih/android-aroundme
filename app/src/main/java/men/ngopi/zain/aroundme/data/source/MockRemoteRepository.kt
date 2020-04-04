package men.ngopi.zain.aroundme.data.source

import io.reactivex.Observable
import men.ngopi.zain.aroundme.data.model.PointLocation

class MockRemoteRepository() {
    fun getPoints(location: PointLocation): Observable<List<PointLocation>> {
        val points = mutableListOf<PointLocation>()
        val latNumber: MutableSet<Double> = mutableSetOf()
        val longNumber: MutableSet<Double> = mutableSetOf()
        while (latNumber.size < 5) {
            latNumber.add((-20..20).random() * 0.0003)
        }
        while (longNumber.size < 5) {
            longNumber.add((-20..20).random() * 0.0005)
        }
        val latList = latNumber.toList()
        val longList = longNumber.toList()
        for (i in 1..5) {
            points.add(
                PointLocation(
                    location.lat + latList[i - 1],
                    location.long + longList[i - 1],
                    "Blue Points $i"
                )
            )
        }
        return Observable.just(points)
    }
}
