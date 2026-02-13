package ls.gear.flow.ui.screen.base

import androidx.annotation.StringRes
import ls.gear.flow.R

sealed class Message(@StringRes open val messageResId: Int) {

    sealed class Success(@StringRes override val messageResId: Int) : Message(messageResId) {
        object SizesSaved : Success(R.string.sizes_saved)
        object OrderSucceed : Success(R.string.order_succeed)
        object CopyUserId : Success(R.string.user_id_copying_succeed)
    }

    sealed class Error(@StringRes override val messageResId: Int) : Message(messageResId) {
        object Network : Error(R.string.request_time_is_expired)
        object NoRecords : Error(R.string.records_are_not_found)
        object PinCodesDidntMatch : Error(R.string.pin_codes_didnt_match)
        object BackgroundTimeExpired : Error(R.string.background_time_expired)
        object InvalidPinCode : Error(R.string.invalid_pin_code)
    }
}
