package ls.gear.flow.ui.toast

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

fun Context.shortToast(@StringRes messageRes: Int) {
    Toast.makeText(this, messageRes, Toast.LENGTH_SHORT).show()
}
