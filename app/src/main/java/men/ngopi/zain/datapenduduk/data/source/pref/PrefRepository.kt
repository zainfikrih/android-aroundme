package men.ngopi.zain.datapenduduk.data.source.pref

import com.orhanobut.hawk.Hawk
import men.ngopi.zain.datapenduduk.data.model.Province
import men.ngopi.zain.datapenduduk.util.CacheKey

class PrefRepository {
    fun getAllProvince(): List<Province>? = Hawk.get(CacheKey.PROVINCE, null)

    fun setAllProvince(allProvince: List<Province>) = Hawk.put(CacheKey.PROVINCE, allProvince)
}
