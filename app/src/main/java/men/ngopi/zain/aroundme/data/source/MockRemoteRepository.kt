package men.ngopi.zain.aroundme.data.source

import io.reactivex.Observable
import men.ngopi.zain.aroundme.data.model.Location

class MockRemoteRepository() {
    fun getPoints(location: Location): Observable<List<Location>> {
        val points = mutableListOf<Location>()
        val latNumber: MutableSet<Int> = mutableSetOf()
        val longNumber: MutableSet<Int> = mutableSetOf()
        while (latNumber.size < 4) {
            latNumber.add((100..800).random())
        }
        while (longNumber.size < 4) {
            longNumber.add((100..800).random())
        }
        val latList = latNumber.toList()
        val longList = longNumber.toList()
        for (i in 1..4) {
            points.add(
                Location(
                    location.lat + latList[i - 1],
                    location.long + longList[i - 1],
                    "Blue Points $i"
                )
            )
        }
        return Observable.just(points)
    }
}
