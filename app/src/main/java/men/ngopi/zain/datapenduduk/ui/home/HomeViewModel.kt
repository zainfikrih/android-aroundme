package men.ngopi.zain.datapenduduk.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import men.ngopi.zain.datapenduduk.data.mapper.AllProvinceDataMapper
import men.ngopi.zain.datapenduduk.data.model.Province
import men.ngopi.zain.datapenduduk.data.repository.Repository
import men.ngopi.zain.datapenduduk.ui.base.BaseViewModel
import men.ngopi.zain.datapenduduk.util.Constant.ERROR_MESSAGE
import men.ngopi.zain.datapenduduk.util.ext.addCompositeDisposable
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val repository: Repository) : BaseViewModel() {
    private val mAllProvince = MutableLiveData<List<Province>>()
    val allProvince: LiveData<List<Province>>
        get() = mAllProvince

    private val mMessage = MutableLiveData<String>()
    val message: LiveData<String>
        get() = mMessage

    fun loadAllProvince() {
        repository.getAllProvince()
            .observeOn(Schedulers.io())
            .map { AllProvinceDataMapper.transform(it) }
            .switchIfEmpty(Observable.just(listOf()))
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                mAllProvince.postValue(it)
            }, {
                mMessage.postValue(ERROR_MESSAGE)
            }).addCompositeDisposable(compositeDisposable)
    }
}