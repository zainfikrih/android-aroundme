package men.ngopi.zain.aroundme.util.ext

import android.view.View
import com.jakewharton.rxbinding3.view.visibility

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}
