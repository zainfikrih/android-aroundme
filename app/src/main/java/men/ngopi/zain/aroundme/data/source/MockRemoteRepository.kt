package men.ngopi.zain.aroundme.data.source

import io.reactivex.Observable
import men.ngopi.zain.aroundme.data.model.PointLocation

/**
 * This is a mock class to get blue points
 */

class MockRemoteRepository {
    fun getPoints(location: PointLocation): Observable<List<PointLocation>> {
        val points = mutableListOf<PointLocation>()
        val latNumber: MutableSet<Double> = mutableSetOf()
        val longNumber: MutableSet<Double> = mutableSetOf()
        while (latNumber.size < 5) {
            var random = (-20..20).random().toDouble()
            if (random < -1 || random > 1) {
                random *= 0.0003
                if (!latNumber.contains(random)) {
                    latNumber.add(random)
                }
            }
        }
        while (longNumber.size < 5) {
            var random = (-20..20).random().toDouble()
            if (random < -1 || random > 1) {
                random *= 0.0005
                if (!longNumber.contains(random)) {
                    longNumber.add(random)
                }
            }
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
