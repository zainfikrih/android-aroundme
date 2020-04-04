package men.ngopi.zain.aroundme.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import men.ngopi.zain.aroundme.data.model.PointLocation
import men.ngopi.zain.aroundme.data.repository.Repository
import men.ngopi.zain.aroundme.ui.base.BaseViewModel
import men.ngopi.zain.aroundme.util.Constant.ERROR_MESSAGE
import men.ngopi.zain.aroundme.util.ext.addCompositeDisposable

class HomeViewModel constructor(private val repository: Repository) : BaseViewModel() {
    private val mPoints = MutableLiveData<List<PointLocation>>()
    val points: LiveData<List<PointLocation>>
        get() = mPoints

    private val mMessage = MutableLiveData<String>()
    val message: LiveData<String>
        get() = mMessage

    fun getPoints(location: PointLocation) {
        repository.getPoints(location)
            .observeOn(Schedulers.io())
            .switchIfEmpty(Observable.just(listOf()))
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                mPoints.postValue(it)
            }, {
                mMessage.postValue(ERROR_MESSAGE)
            }).addCompositeDisposable(compositeDisposable)
    }
}
