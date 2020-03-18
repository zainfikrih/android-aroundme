package men.ngopi.zain.datapenduduk.data.source.remote.response

import com.google.gson.annotations.SerializedName
import men.ngopi.zain.datapenduduk.data.model.Province

data class AllProvinceResponse(
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("semuaprovinsi")
    val allProvince: List<Province>
)
