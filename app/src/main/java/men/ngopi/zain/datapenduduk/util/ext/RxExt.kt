package men.ngopi.zain.datapenduduk.util.ext

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

fun Disposable.addCompositeDisposable(compositeDisposable: CompositeDisposable) {
    compositeDisposable.add(this)
}