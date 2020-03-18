package men.ngopi.zain.datapenduduk.data.mapper

import men.ngopi.zain.datapenduduk.data.model.Province

object AllProvinceDataMapper {
    fun transform(value: List<Province>) = value.map {
        Province(
            it.id,
            it.name
        )
    }
}
