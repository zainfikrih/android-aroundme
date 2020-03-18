package men.ngopi.zain.datapenduduk.data.source.remote.retrofit

import io.reactivex.Observable
import men.ngopi.zain.datapenduduk.data.source.remote.response.AllProvinceResponse
import retrofit2.http.GET

interface RetrofitServices {

    @GET("daerahindonesia/provinsi")
    fun getAllProvince(): Observable<AllProvinceResponse>
}
